package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.logic.CptService;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteAlias;

import java.util.ArrayList;
import java.util.List;

@Route(value = "", layout = MainView.class)
@RouteAlias("dashboard")
@PageTitle("Dashboard")
public class Dashboard extends VerticalLayout {

    CptService service;

    Board board = new Board();

    public Dashboard(CptService service) {
        this.service = service;
        addClassName("dashboard-view");
        board.addRow(createHeader("Overview", "Total"));
        board.addRow(createSingleCard("Invested Capital", service.getTotalInvestedCapital().toString() + " CHF"), createSingleCard("Current Value", service.getTotalCurrentValue().toString() + " CHF", service.getTotalPercentageChange().doubleValue()) /*,createSingleCard("Return rate", service.getTotalPercentageChange() + " %") */);
        board.addRow(createHeader("Platforms", "Platform total"));
        createPlatformOverview();
        //board.addRow(createViewEvents());
        //board.addRow(/*createServiceHealth()*/ createResponseTimes());
        add(board);
    }

    private Component createSingleCard(String title, String value) {

        H2 h2 = new H2(title);
        h2.addClassNames("font-normal", "m-0", "text-secondary", "text-xs");

        Span span = new Span(value);
        span.addClassNames("font-semibold", "text-3xl");

        VerticalLayout layout = new VerticalLayout(h2, span);
        layout.addClassName("p-l");
        layout.setPadding(false);
        layout.setSpacing(false);
        return layout;
    }
    private Component createSingleCard(String title, String value ,double percentage) {
        VaadinIcon icon = VaadinIcon.ARROW_UP;
        String prefix = "";
        String theme = "badge";

        if (percentage == 0) {
            prefix = "±";
        } else if (percentage > 0) {
            prefix = "+";
            theme += " success";
        } else if (percentage < 0) {
            icon = VaadinIcon.ARROW_DOWN;
            theme += " error";
        }

        H2 h2 = new H2(title);
        h2.addClassNames("font-normal", "m-0", "text-secondary", "text-xs");

        Span span = new Span(value);
        span.addClassNames("font-semibold", "text-3xl");

        Icon i = icon.create();
        i.addClassNames("box-border", "p-xs");

        Span badge = new Span(i, new Span(prefix + percentage +"%"));
        badge.getElement().getThemeList().add(theme);

        VerticalLayout layout = new VerticalLayout(h2, span, badge);
        layout.addClassName("p-l");
        layout.setPadding(false);
        layout.setSpacing(false);
        return layout;
    }

    private Component createTrippleCard(String title, String key, String value, String key2, String value2, double percentage) {
        VaadinIcon icon = VaadinIcon.ARROW_UP;
        String prefix = "";
        String theme = "badge";

        if (percentage == 0) {
            prefix = "±";
        } else if (percentage > 0) {
            prefix = "+";
            theme += " success";
        } else if (percentage < 0) {
            icon = VaadinIcon.ARROW_DOWN;
            theme += " error";
        }

        H3 h3 = new H3(title);
        h3.addClassNames("font-normal", "m-0", "py-m", "text-secondary", "text-xl");

        Span span1 = new Span(key);
        span1.addClassNames("font-normal", "text-xs", "py-xs");

        Span span2 = new Span(value);
        span2.addClassNames("font-semibold", "text-2xl");

        Span span3 = new Span(key2);
        span3.addClassNames("font-normal", "text-xs", "py-xs");

        Span span4 = new Span(value2);
        span4.addClassNames("font-semibold", "text-2xl");

        Icon i = icon.create();
        i.addClassNames("box-border", "p-xs");

        Span badge = new Span(i, new Span(prefix + percentage + "%"));
        badge.getElement().getThemeList().add(theme);

        VerticalLayout layout = new VerticalLayout(h3, span1, span2, span3, span4 , badge);
        layout.addClassName("p-l");
        layout.setPadding(false);
        layout.setSpacing(false);
        return layout;
    }

    private void createPlatformOverview() {

        List<Platform> list = service.findAllCryptoPlatforms();

        ArrayList<Component> cList = new ArrayList<Component>();

        int i = 1;

        int s = list.size();

        for (Platform p : list) {

            cList.add(createTrippleCard(p.getName(), "Invested Capital",p.getInvestedCapitalFiat(service) + " CHF", "Current Value", p.getCurrentValueFiat(service) + " CHF" ,service.getPlatformPercentageChange(p)));
            if (i == s) {
                if (i == 1) {
                    board.addRow(cList.get(0));
                } else if (i == 2) {
                    board.addRow(cList.get(0), cList.get(1));
                } else if (i == 3) {
                    board.addRow(cList.get(0), cList.get(1), cList.get(2));
                } else {
                    board.addRow(cList.get(0), cList.get(1), cList.get(2), cList.get(3));
                    cList.remove(cList);
                }

            }
            i++;

        }
    }

    private HorizontalLayout createHeader(String title, String subtitle) {
        H2 h2 = new H2(title);
        h2.addClassNames("text-xl", "m-0");

        Span span = new Span(subtitle);
        span.addClassNames("text-secondary", "text-xs");

        VerticalLayout column = new VerticalLayout(h2, span);
        column.setPadding(false);
        column.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout(column);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.setSpacing(false);
        header.setWidthFull();
        header.setPadding(true);
        return header;
    }

}