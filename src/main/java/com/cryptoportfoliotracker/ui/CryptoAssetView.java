package com.cryptoportfoliotracker.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="cryptoassets", layout = MainView.class)
@PageTitle("Crypto Assets")
public class CryptoAssetView extends VerticalLayout {

    public CryptoAssetView() {
        add(new H1("My Crypto Assets"));
    }
}
