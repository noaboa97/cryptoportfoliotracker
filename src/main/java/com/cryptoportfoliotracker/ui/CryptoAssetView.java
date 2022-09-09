package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.logic.CptService;
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

@Route(value="cryptoassets", layout = MainView.class)
@PageTitle("Crypto Assets")
public class CryptoAssetView extends VerticalLayout {

    Grid<CryptoAsset> grid = new Grid<>(CryptoAsset.class, false);

    TextField filterText = new TextField();

    CompAddCryptoAsset compAddCryptoAsset;

    CptService service;

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
    
    private void configureGrid() {
        grid.addClassNames("cryptoasset-grid");
        grid.setSizeFull();

        grid.addColumn(Asset::getFullName).setHeader("Name");
        grid.addColumn(Asset::getShortName).setHeader("Abbr.");
        grid.addColumn(Asset::getPlatform).setHeader("Platform");
        grid.addColumn(asset -> asset.getInvestedCapitalFiat(service).stripTrailingZeros() + " " + service.findStandard().getShortName()).setHeader("Invested Capital Fiat");
        grid.addColumn(asset -> asset.getInvestedCapitalCrypto(service).stripTrailingZeros() + " " + asset.getShortName()).setHeader("Invested Capital Crypto");
        grid.addColumn(asset -> asset.getCurrentValueFiat(service).stripTrailingZeros() + " " + service.findStandard().getShortName()).setHeader("Current Total Value Fiat");
        //grid.addColumn(asset -> asset.getCurrentValueFiat()).setHeader("Current Value");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editCryptoAsset(event.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, compAddCryptoAsset);
        content.setFlexGrow(2, grid);

        content.setFlexGrow(1, compAddCryptoAsset);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

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

    public void editCryptoAsset(CryptoAsset transaction) {


        if (transaction == null) {
            closeEditor();
        } else {
            compAddCryptoAsset.setCryptoAsset(transaction);
            compAddCryptoAsset.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        compAddCryptoAsset.setCryptoAsset(null);
        compAddCryptoAsset.setVisible(false);
        removeClassName("editing");
    }

    private void addCryptoAsset() {


        grid.asSingleSelect().clear();
        editCryptoAsset(new CryptoAsset());
    }


    private void configureCompAddCryptoAsset() {

        compAddCryptoAsset = new CompAddCryptoAsset(service.findAllPlatforms());
        compAddCryptoAsset.setWidth("25em");
        compAddCryptoAsset.addListener(CompAddCryptoAsset.SaveEvent.class, this::saveCryptoAsset);
        compAddCryptoAsset.addListener(CompAddCryptoAsset.DeleteEvent.class, this::deleteCryptoAsset);
        compAddCryptoAsset.addListener(CompAddCryptoAsset.CloseEvent.class, e -> closeEditor());

    }

    public void updateList() {

        grid.setItems(service.findAllCryptoAssets(filterText.getValue()));

    }


    private void saveCryptoAsset(CompAddCryptoAsset.SaveEvent event) {
        event.getCryptoAsset();
        service.saveCryptoAsset(event.getCryptoAsset());
        updateList();
        closeEditor();
    }

    private void deleteCryptoAsset(CompAddCryptoAsset.DeleteEvent event) {
        service.deleteCryptoAsset(event.getCryptoAsset());
        updateList();
        closeEditor();
    }





}
