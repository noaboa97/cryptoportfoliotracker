package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.logic.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

/***
 * Class to display the transaction view in the ui
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see CompAddTransaction
 * @see MainView
 */
@PageTitle("Transactions")
@Route(value = "transactions", layout = MainView.class)
public class TransactionView extends VerticalLayout {

    Grid<Transaction> grid = new Grid<>(Transaction.class, false);
    TextField filterText = new TextField();

    CompAddTransaction compAddTransaction;
    CptService service;

    /**
     * Creates the transaction view
     *
     * @param service of platforms
     * @see CptService
     */
    public TransactionView(CptService service) {
        this.service = service;

        add(new H2("Transactions"));

        addClassName("transaction-view");
        setSizeFull();
        configureGrid();
        configureCompAddTransaction();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();

    }

    /**
     * Configures the grid / table
     */
    private void configureGrid() {
        grid.addClassNames("transaction-grid");
        grid.setSizeFull();

        /** Sets the column object type, method to display and the header of the column */
        grid.addColumn(transaction -> transaction.getStringDate()).setHeader("Timestamp");
        grid.addColumn(transaction -> transaction.getSrcAmount().stripTrailingZeros()).setHeader("Source Amount");
        grid.addColumn(transaction -> transaction.getSrcAsset()).setHeader("Source Asset");
        grid.addColumn(transaction -> transaction.getSrcPlatform()).setHeader("Source Platform");
        grid.addColumn(transaction -> transaction.getDestAmount().stripTrailingZeros()).setHeader("Destination Amount");
        grid.addColumn(transaction -> transaction.getDestAsset()).setHeader("Destination Asset");
        grid.addColumn(transaction -> transaction.getDestPlatform().getName()).setHeader("Destination Platform");
        grid.addColumn(transaction -> transaction.getFee().stripTrailingZeros()).setHeader("Fees");
        grid.addColumn(transaction -> transaction.getFeeAsset()).setHeader("Fee Asset");
        grid.addColumn(transaction -> transaction.getNotes()).setHeader("Notes");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        /** Event listener when a row is selected to open the editor */
        grid.asSingleSelect().addValueChangeListener(event -> editTransaction(event.getValue()));
    }

    /**
     * Creates the layout for the site
     *
     * @return Component for the site
     */
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, compAddTransaction);
        content.setFlexGrow(2, grid);

        content.setFlexGrow(1, compAddTransaction);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    /**
     * Creates the toolbar with filter and add button
     *
     * @return returns the horizontal layout for the toolbar
     */
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add transaction");
        addContactButton.addClickListener(click -> addTransaction());


        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);


        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /** Allows to edit the transaction
     * Also loads the list of assets of the platform and sets those
     *
     * @param transaction
     * */
    public void editTransaction(Transaction transaction) {


        if (transaction == null) {
            closeEditor();
        } else {
            List<Asset> destPlatformList = service.findAllAssetsOfPlatform(transaction.getDestPlatform());
            List<Asset> srcPlatformList = service.findAllAssetsOfPlatform(transaction.getSrcPlatform());

            compAddTransaction.destAsset.setItems(destPlatformList);
            compAddTransaction.srcAsset.setItems(srcPlatformList);
            compAddTransaction.srcAsset.setItemLabelGenerator(Asset::getShortName);
            compAddTransaction.destAsset.setItemLabelGenerator(Asset::getShortName);
            compAddTransaction.setTransaction(transaction);
            compAddTransaction.setVisible(true);
            addClassName("editing");
        }
    }

    /** closes the editor */
    private void closeEditor() {
        compAddTransaction.setTransaction(null);
        compAddTransaction.setVisible(false);
        removeClassName("editing");
    }

    /** Method used to add a new transaction */
    private void addTransaction() {


        grid.asSingleSelect().clear();
        editTransaction(new Transaction());
    }

    /** Configures the editor and defines the events for the buttons */
    private void configureCompAddTransaction() {

        compAddTransaction = new CompAddTransaction(service.findAllAssets(), service.findAllPlatforms());
        compAddTransaction.setWidth("25em");
        compAddTransaction.addListener(CompAddTransaction.SaveEvent.class, this::saveTransaction);
        compAddTransaction.addListener(CompAddTransaction.DeleteEvent.class, this::deleteTransaction);
        compAddTransaction.addListener(CompAddTransaction.CloseEvent.class, e -> closeEditor());

        compAddTransaction.addListener(CompAddTransaction.DestPlatformEvent.class, this::getAssetofDestPlatform);
        compAddTransaction.addListener(CompAddTransaction.SrcPlatformEvent.class, this::getAssetOfSrcPlatform);


    }

    /** Loads the assets of the destination platform after the value changed in editor
     *
     * @param event with all the data
     * @see CompAddTransaction.DestPlatformEvent
     * @see CptService
     * */
    private void getAssetofDestPlatform(CompAddTransaction.DestPlatformEvent event) {
        List<Asset> platformlist = service.findAllAssetsOfPlatform(event.getPlatform());

        event.getAsset().setItems(platformlist);

    }

    /** Loads the assets of the source platform after the value changed in editor
     *
     * @param event with all the data
     * @see CompAddTransaction.SrcPlatformEvent
     * @see CptService
     * */
    private void getAssetOfSrcPlatform(CompAddTransaction.SrcPlatformEvent event) {
        List<Asset> platformlist = service.findAllAssetsOfPlatform(event.getPlatform());

        event.getAsset().setItems(platformlist);

    }

    /** Updates the table / grid */
    public void updateList() {

        grid.setItems(service.findAllTransactions(filterText.getValue()));

    }

    /** Method which is called from the editor when a transaction is saved
     *
     * @param event with all the data which where entered into the editor
     * @see CompAddCryptoAsset.SaveEvent
     */
    private void saveTransaction(CompAddTransaction.SaveEvent event) {
        service.saveTransaction(event.getTransaction());
        updateList();
        closeEditor();
    }

    /** Method which is called from the editor when a transaction is deleted
     *
     * @param event with all the data which where entered into the editor
     * @see CompAddCryptoAsset.DeleteEvent
     * */
    private void deleteTransaction(CompAddTransaction.DeleteEvent event) {
        service.deleteTransaction(event.getTransaction());
        updateList();
        closeEditor();
    }


}