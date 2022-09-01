package com.cryptoportfoliotracker.ui;

import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;

@Route(value = "dashboard", layout = MainView.class)
@PageTitle("Dashboard")
public class Dashboard extends VerticalLayout {

    public Dashboard() {
        add(new H2("Dashboard"));
        createcard();
    }


    public void createcard() {
        Board board = new Board();

        Component c1 = new CardIndicator("Current users", "745", "+33.7");
        Component c2 = new CardIndicator("View events", "54.6k", "-112.45");
        Component c3 = new CardIndicator("Conversion rate", "18%", "+3.9");
        Component c4 = new CardIndicator("Custom metric", "-123.45");

        board.addRow(c1);

        board.addRow(new CardChart());

        add(board);
    }
}