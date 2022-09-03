package com.cryptoportfoliotracker.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainView extends AppLayout {

    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Crypto Portfolio Tracker");
        logo.addClassNames("text-l", "m-0");

        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink dashboard = new RouterLink("Dashboard", Dashboard.class);
        RouterLink cryptoAssetView = new RouterLink("Crypto Assets", CryptoAssetView.class);
        RouterLink TransactionView = new RouterLink("Transactions", com.cryptoportfoliotracker.ui.TransactionView.class);

        TransactionView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                dashboard,
                cryptoAssetView,
                TransactionView
        ));
    }
}