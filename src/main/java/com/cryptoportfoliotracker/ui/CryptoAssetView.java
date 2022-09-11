package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.logic.CptService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/***
 * Class to display the crypto asset view in the ui
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see CompAddCryptoAsset
 * @see MainView
 */
@Route(value = "cryptoassets", layout = MainView.class)
@PageTitle("Crypto Assets")
public class CryptoAssetView extends VerticalLayout {

    Grid<CryptoAsset> grid = new Grid<>(CryptoAsset.class, false);

    TextField filterText = new TextField();

    CompAddCryptoAsset compAddCryptoAsset;

    CptService service;

    /**
     * Creates the crypto asset view
     *
     * @param service of platforms
     * @see CptService
     */
    public CryptoAssetView(CptService service) {
        this.service = service;
        add(new H2("My Crypto Assets"));

        addClassName("cryptoasset-view");
        setSizeFull();
        configureGrid();
        configureCompAddCryptoAsset();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();


    }

    /**
     * Configures the grid / table
     */
    private void configureGrid() {
        grid.addClassNames("cryptoasset-grid");
        grid.setSizeFull();

        /** Sets the column object type, method to display and the header of the column */
        grid.addColumn(Asset::getFullName).setHeader("Name");
        grid.addColumn(Asset::getShortName).setHeader("Abbr.");
        grid.addColumn(Asset::getPlatform).setHeader("Platform");
        grid.addColumn(cryptoAsset -> cryptoAsset.getCurrentValueFiat() + " " + service.findStandard().getShortName()).setHeader("Current Market Price").setTextAlign(ColumnTextAlign.END);
        grid.addColumn(asset -> asset.getInvestedCapitalFiatToString(service) + " " + service.findStandard().getShortName()).setHeader("Invested Capital Fiat").setTextAlign(ColumnTextAlign.END);
        grid.addColumn(asset -> asset.getInvestedCapitalCryptoToString(service) + " " + asset.getShortName()).setHeader("Invested Capital Crypto").setTextAlign(ColumnTextAlign.END);
        grid.addColumn(asset -> asset.getCurrentValueFiatToString(service) + " " + service.findStandard().getShortName()).setHeader("Current Total Value Fiat").setTextAlign(ColumnTextAlign.END);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        /** Event listener when a row is selected to open the editor */
        grid.asSingleSelect().addValueChangeListener(event -> editCryptoAsset(event.getValue()));
    }

    /**
     * Creates the layout for the site
     *
     * @return Component for the site
     */
    private Component getContent() {
        /** Adds the grid and the component to a horizontal layout */
        HorizontalLayout content = new HorizontalLayout(grid, compAddCryptoAsset);
        content.setFlexGrow(2, grid);

        content.setFlexGrow(1, compAddCryptoAsset);
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

        Button addContactButton = new Button("Add Crypto Asset");
        addContactButton.addClickListener(click -> addCryptoAsset());


        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);


        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /**
     * Allows to edit the crypto asset
     */
    public void editCryptoAsset(CryptoAsset transaction) {


        if (transaction == null) {
            closeEditor();
        } else {
            compAddCryptoAsset.setCryptoAsset(transaction);
            compAddCryptoAsset.setVisible(true);
            addClassName("editing");
        }
    }

    /**
     * closes the editor
     */
    private void closeEditor() {
        compAddCryptoAsset.setCryptoAsset(null);
        compAddCryptoAsset.setVisible(false);
        removeClassName("editing");
    }

    /**
     * Method used to add a new crypto asset
     */
    private void addCryptoAsset() {


        grid.asSingleSelect().clear();
        editCryptoAsset(new CryptoAsset());
    }

    /**
     * Configures the editor and defines the events for the buttons
     */
    private void configureCompAddCryptoAsset() {

        compAddCryptoAsset = new CompAddCryptoAsset(service.findAllPlatforms());
        compAddCryptoAsset.setWidth("25em");
        compAddCryptoAsset.addListener(CompAddCryptoAsset.SaveEvent.class, this::saveCryptoAsset);
        compAddCryptoAsset.addListener(CompAddCryptoAsset.DeleteEvent.class, this::deleteCryptoAsset);
        compAddCryptoAsset.addListener(CompAddCryptoAsset.CloseEvent.class, e -> closeEditor());

    }

    /**
     * Updates the table / grid
     */
    public void updateList() {

        grid.setItems(service.findAllCryptoAssets(filterText.getValue()));

    }

    /**
     * Method which is called from the editor when a asset is saved
     *
     * @param event with all the data which where entered into the editor
     * @see CompAddCryptoAsset.SaveEvent
     */
    private void saveCryptoAsset(CompAddCryptoAsset.SaveEvent event) {
        event.getCryptoAsset();
        service.saveCryptoAsset(event.getCryptoAsset());
        updateList();
        closeEditor();
    }

    /**
     * Method which is called from the editor when a asset is deleted
     *
     * @param event with all the data which where entered into the editor
     */
    private void deleteCryptoAsset(CompAddCryptoAsset.DeleteEvent event) {
        service.deleteCryptoAsset(event.getCryptoAsset());
        updateList();
        closeEditor();
    }

}
