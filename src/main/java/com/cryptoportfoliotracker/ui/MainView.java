package com.cryptoportfoliotracker.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

/***
 * Class to create the app layout which is always around the other sites.
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
public class MainView extends AppLayout {

    /**
     * Constructor which calls the other methods
     */
    public MainView() {
        createHeader();
        createDrawer();
    }

    /**
     * Creates the header for the application with the burger menu and title
     */
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

    /**
     * Creates the navigation for the application
     *
     * @see Dashboard
     * @see CryptoAssetView
     * @see TransactionView
     */
    private void createDrawer() {
        RouterLink dashboard = new RouterLink("Dashboard", Dashboard.class);
        dashboard.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink cryptoAssetView = new RouterLink("Crypto Assets", CryptoAssetView.class);
        cryptoAssetView.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink TransactionView = new RouterLink("Transactions", com.cryptoportfoliotracker.ui.TransactionView.class);
        TransactionView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                dashboard,
                cryptoAssetView,
                TransactionView
        ));
    }
}