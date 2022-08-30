package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.logic.*;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.cryptoportfoliotracker.repository.TransactionRepository;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Transactions")
@Route(value = "", layout = MainView.class)
public class ListView extends VerticalLayout {

    Grid<Transaction> grid = new Grid<>(Transaction.class, false);
    TextField filterText = new TextField();

    CompAddTransaction compAddTransaction;
    CptService service;

    public ListView(CptService service) {
        this.service = service;




        add(new H2("Transactions"));

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureCompAddTransaction();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();




    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();

        grid.addColumn(transaction -> transaction.getDateAndTime()).setHeader("Timestamp");
        grid.addColumn(transaction -> transaction.getSrcAsset().getShortname()).setHeader("Source Asset");
        grid.addColumn(transaction -> transaction.getDestAsset()).setHeader("Destination Asset");
        grid.addColumn(transaction -> transaction.getSrcAmount()).setHeader("Source Amount");
        grid.addColumn(transaction -> transaction.getDestAmount()).setHeader("Destination Amount");
        grid.addColumn(transaction -> transaction.getSrcPlatform().getName()).setHeader("Source Platform");
        grid.addColumn(transaction -> transaction.getDestPlatform()).setHeader("Destination Platform");
        grid.addColumn(transaction -> transaction.getFees()).setHeader("Fees");
        grid.addColumn(transaction -> transaction.getFeeAsset().getShortname()).setHeader("Fee Asset");
        grid.addColumn(transaction -> transaction.getNotes()).setHeader("Notes");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editTransaction(event.getValue()));

    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, compAddTransaction);
        content.setFlexGrow(2, grid);

        content.setFlexGrow(1, compAddTransaction);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

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

    public void editTransaction(Transaction transaction) {


        if (transaction == null) {
            closeEditor();
        } else {
            compAddTransaction.setTransaction(transaction);
            compAddTransaction.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        compAddTransaction.setTransaction(null);
        compAddTransaction.setVisible(false);
        removeClassName("editing");
    }

    private void addTransaction() {


        grid.asSingleSelect().clear();
        editTransaction(new Transaction());
    }


    private void configureCompAddTransaction() {

        compAddTransaction = new CompAddTransaction(service.findAllCryptoAssets(),service.findAllPlatforms());
        compAddTransaction.setWidth("25em");
        compAddTransaction.addListener(CompAddTransaction.SaveEvent.class, this::saveTransaction);


        compAddTransaction.addListener(CompAddTransaction.DeleteEvent.class, this::deleteTransaction);


        compAddTransaction.addListener(CompAddTransaction.CloseEvent.class, e -> closeEditor());


    }

    public void updateList() {

        grid.setItems(service.findAllTransactions(filterText.getValue()));

    }


    private void saveTransaction(CompAddTransaction.SaveEvent event) {
        service.saveTransaction(event.getTransaction());
        updateList();
        closeEditor();
    }

    private void deleteTransaction(CompAddTransaction.DeleteEvent event) {
        service.deleteTransaction(event.getTransaction());
        updateList();
        closeEditor();
    }


}