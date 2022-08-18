package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.repository.Transactions;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Transactions")
@Route(value = "",layout = MainView.class)
public class ListView extends VerticalLayout {


    Grid<Transactions> grid = new Grid<>(Transactions.class);



    public ListView() {
        add(new H2("Transactions"));





    }


}