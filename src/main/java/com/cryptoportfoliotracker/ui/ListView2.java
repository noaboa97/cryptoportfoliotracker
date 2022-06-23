package com.cryptoportfoliotracker.ui;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="", layout = MainView.class)
@PageTitle("Contacts | Vaadin CRM")
public class ListView2 extends VerticalLayout {


    public ListView2() {
        add(new H1("Home"));
    }



}