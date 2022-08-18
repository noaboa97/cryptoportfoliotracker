package com.cryptoportfoliotracker.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="dashboard", layout = MainView.class)
@PageTitle("Dashboard")
public class Dashboard extends VerticalLayout {

    public Dashboard() {
        add(new H1("Dashboard"));
    }

}