package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.Transaction;
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
@Route(value = "",layout = MainView.class)
public class ListView extends VerticalLayout {


    Grid<Transaction> grid = new Grid<>(Transaction.class, false);
    TextField filterText = new TextField();
    public ListView() {

        add(new H2("Transactions"));

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();

        grid.addColumn(transaction -> transaction.getDateAndTime()).setHeader("Timestamp");
        grid.addColumn(transaction -> transaction.getSrcAsset()).setHeader("Source Asset");
        grid.addColumn(transaction -> transaction.getDestAsset()).setHeader("Destination Asset");
        grid.addColumn(transaction -> transaction.getSrcAmount()).setHeader("Source Amount");
        grid.addColumn(transaction -> transaction.getDestAmount()).setHeader("Destination Amount");
        grid.addColumn(transaction -> transaction.getSrcPlatform()).setHeader("Source Platform");
        grid.addColumn(transaction -> transaction.getDestPlatform()).setHeader("Destination Platform");
        grid.addColumn(transaction -> transaction.getFees()).setHeader("Fees");
        grid.addColumn(transaction -> transaction.getFeeAsset()).setHeader("Fee Asset");
        grid.addColumn(transaction -> transaction.getNotes()).setHeader("Notes");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);



        Button addContactButton = new Button("Add contact");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);


        toolbar.addClassName("toolbar");
        return toolbar;
    }


}