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
        board.addRow(createSingleCard("Invested Capital", service.getTotalInvestedCapital().toString() + " CHF"), createSingleCard("Current Value", service.getTotalCurrentValue().toString() + " CHF"), createSingleCard("Return rate", service.getTotalPercentageChange() + " %"), createSingleCard("Custom metric", "-123.45"));
        board.addRow(createHeader("Platforms", "Platform total"));
        createPlatformOverview();
        //board.addRow(createViewEvents());
        //board.addRow(/*createServiceHealth()*/ createResponseTimes());
        add(board);
    }

    private Component createSingleCard(String title, String value /*,String percentage*/) {
        VaadinIcon icon = VaadinIcon.ARROW_UP;
        String prefix = "";
        String theme = "badge";
/*
        if (percentage == 0) {
            prefix = "±";
        } else if (percentage > 0) {
            prefix = "+";
            theme += " success";
        } else if (percentage < 0) {
            icon = VaadinIcon.ARROW_DOWN;
            theme += " error";
        }*/

        H2 h2 = new H2(title);
        h2.addClassNames("font-normal", "m-0", "text-secondary", "text-xs");

        Span span = new Span(value);
        span.addClassNames("font-semibold", "text-3xl");

        Icon i = icon.create();
        i.addClassNames("box-border", "p-xs");

        //Span badge = new Span(i, new Span(prefix + percentage.toString()));
        //badge.getElement().getThemeList().add(theme);

        VerticalLayout layout = new VerticalLayout(h2, span /*, badge*/);
        layout.addClassName("p-l");
        layout.setPadding(false);
        layout.setSpacing(false);
        return layout;
    }

    private Component createTrippleCard(String title, String value1, String value2) {
        VaadinIcon icon = VaadinIcon.ARROW_UP;
        String prefix = "";
        String theme = "badge";
/*
        if (percentage == 0) {
            prefix = "±";
        } else if (percentage > 0) {
            prefix = "+";
            theme += " success";
        } else if (percentage < 0) {
            icon = VaadinIcon.ARROW_DOWN;
            theme += " error";
        }*/

        H3 h3 = new H3(title);
        h3.addClassNames("font-normal", "m-0", "text-secondary", "text-xs");

        Span span1 = new Span(value1);
        span1.addClassNames("font-semibold", "text-3xl");

        Span span2 = new Span(value2);
        span2.addClassNames("font-semibold", "text-3xl");

        Span span3 = new Span(value2);
        span2.addClassNames("font-semibold", "text-3xl");

        Icon i = icon.create();
        i.addClassNames("box-border", "p-xs");

        //Span badge = new Span(i, new Span(prefix + percentage.toString()));
        //badge.getElement().getThemeList().add(theme);

        VerticalLayout layout = new VerticalLayout(h3, span1, span2 /*, badge*/);
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

            cList.add(createTrippleCard(p.getName(), "Invested Capital: " + p.getInvestedCapitalFiat(service) + " CHF", "Current Value: " + p.getCurrentValueFiat(service) + " CHF"));
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

/*
    private Component createViewEvents() {
        // Header

        Select year = new Select();
        year.setItems("2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021");
        year.setValue("2021");
        year.setWidth("100px");

        HorizontalLayout header = createHeader("Portfolio Overview", "Platform ");
        //header.add(year);

        // Chart
        Chart chart = new Chart(ChartType.AREASPLINE);
        Configuration conf = chart.getConfiguration();
        conf.getChart().setStyledMode(true);

        XAxis xAxis = new XAxis();
        xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        conf.addxAxis(xAxis);

        conf.getyAxis().setTitle("Values");

        PlotOptionsAreaspline plotOptions = new PlotOptionsAreaspline();
        plotOptions.setPointPlacement(PointPlacement.ON);
        plotOptions.setMarker(new Marker(false));
        conf.addPlotOptions(plotOptions);


        for (Platform p : service.findAllPlatforms()) {
            conf.addSeries(new ListSeries(p.getName(), 189, 191, 291, 396, 501, 403, 609, 712, 729, 942, 1044, 1247)); // Collection of Number
        }
        //conf.addSeries(new ListSeries("London", 138, 246, 248, 348, 352, 353, 463, 573, 778, 779, 885, 887));
        //conf.addSeries(new ListSeries("New York", 65, 65, 166, 171, 293, 302, 308, 317, 427, 429, 535, 636));
        //conf.addSeries(new ListSeries("Tokyo", 0, 11, 17, 123, 130, 142, 248, 349, 452, 454, 458, 462));

        // Add it all together
        VerticalLayout viewEvents = new VerticalLayout(header, chart);
        viewEvents.addClassName("p-l");
        viewEvents.setPadding(false);
        viewEvents.setSpacing(false);
        viewEvents.getElement().getThemeList().add("spacing-l");
        return viewEvents;
    }*/

    /*
    private Component createServiceHealth() {
        // Header
        HorizontalLayout header = createHeader("Service health", "Input / output");

        // Grid
        Grid<ServiceHealth> grid = new Grid();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setAllRowsVisible(true);

        grid.addColumn(new ComponentRenderer<>(serviceHealth -> {
            Span status = new Span();
            String statusText = getStatusDisplayName(serviceHealth);
            status.getElement().setAttribute("aria-label", "Status: " + statusText);
            status.getElement().setAttribute("title", "Status: " + statusText);
            status.getElement().getThemeList().add(getStatusTheme(serviceHealth));
            return status;
        })).setHeader("").setFlexGrow(0).setAutoWidth(true);
        grid.addColumn(ServiceHealth::getCity).setHeader("City").setFlexGrow(1);
        grid.addColumn(ServiceHealth::getInput).setHeader("Input").setAutoWidth(true).setTextAlign(ColumnTextAlign.END);
        grid.addColumn(ServiceHealth::getOutput).setHeader("Output").setAutoWidth(true)
                .setTextAlign(ColumnTextAlign.END);

        grid.setItems(new ServiceHealth(Status.EXCELLENT, "Münster", 324, 1540),
                new ServiceHealth(Status.OK, "Cluj-Napoca", 311, 1320),
                new ServiceHealth(Status.FAILING, "Ciudad Victoria", 300, 1219));

        // Add it all together
        VerticalLayout serviceHealth = new VerticalLayout(header, grid);
        serviceHealth.addClassName("p-l");
        serviceHealth.setPadding(false);
        serviceHealth.setSpacing(false);
        serviceHealth.getElement().getThemeList().add("spacing-l");
        return serviceHealth;
    }

    private Component createResponseTimes() {
        HorizontalLayout header = createHeader("Response times", "Average across all systems");

        // Chart
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        conf.getChart().setStyledMode(true);
        chart.setThemeName("gradient");

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("System 1", 12.5));
        series.add(new DataSeriesItem("System 2", 12.5));
        series.add(new DataSeriesItem("System 3", 12.5));
        series.add(new DataSeriesItem("System 4", 12.5));
        series.add(new DataSeriesItem("System 5", 12.5));
        series.add(new DataSeriesItem("System 6", 12.5));
        conf.addSeries(series);

        // Add it all together
        VerticalLayout serviceHealth = new VerticalLayout(header, chart);
        serviceHealth.addClassName("p-l");
        serviceHealth.setPadding(false);
        serviceHealth.setSpacing(false);
        serviceHealth.getElement().getThemeList().add("spacing-l");
        return serviceHealth;
    }
*/
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
        return header;
    }

}