package com.cryptoportfoliotracker.ui;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;


//@Route("")
public class MainView extends AppLayout {

    public MainView(){
        createHeader();
        createDrawer();
        //add(new H1("Home"));

    }

    private void createHeader(){
        H1 logo = new H1("Crypto Portfolio Tracker");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                logo


        );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        
        addToNavbar(header);


    }

    private void createDrawer(){

        RouterLink listLink = new RouterLink("Test", ListView2.class);
        //listLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(
                listLink
        );



    }

}
