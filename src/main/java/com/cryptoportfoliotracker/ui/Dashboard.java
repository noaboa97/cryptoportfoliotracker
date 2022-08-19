package com.cryptoportfoliotracker.ui;

import com.vaadin.board.Board;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.ui.Component;

@Route(value="dashboard", layout = MainView.class)
@PageTitle("Dashboard")
public class Dashboard extends VerticalLayout {

    public Dashboard() {
        add(new H2("Dashboard"));
    }

    /*
    public void createcard() {
        Board board = new Board();



        board.addRow();
                //new CardIndicator("Current users", "745", "+33.7"),
                //(Component) new CardIndicator("View events", "54.6k", "-112.45"),
                //(Component) new CardIndicator("Conversion rate", "18%", "+3.9"),
                //(Component) new CardIndicator("Custom metric", "-123.45")
                //(Component) Indicator

        //board.addRow(new ExampleChart());
    }*/
}