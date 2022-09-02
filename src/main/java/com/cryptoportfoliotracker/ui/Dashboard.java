package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.logic.CptService;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Route(value = "dashboard", layout = MainView.class)
@PageTitle("Dashboard")
public class Dashboard extends VerticalLayout {

    CptService service;





    public Dashboard(CptService service) {
        this.service = service;
        addClassName("dashboard-view");


        Board board = new Board();
        board.addRow(createHighlight("Invested Capital", getInvestedCapital().toString()), createHighlight("Current Value", getCurrentValue().toString()),
                createHighlight("Return rate", getReturnRate()+"%"), createHighlight("Custom metric", "-123.45"));
        //board.addRow(createViewEvents());
        //board.addRow(/*createServiceHealth()*/ createResponseTimes());
        add(board);
    }

    private BigDecimal getReturnRate() {
        BigDecimal returnRate;
        if(getInvestedCapital().compareTo(getCurrentValue()) < 0) {
            returnRate = getInvestedCapital().divide(getCurrentValue(), 1, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100"));
        }else{
            returnRate = getCurrentValue().divide(getInvestedCapital(), 1, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100"));
        }

        return returnRate;
    }

    private BigDecimal PercentageChange (){
        BigDecimal increase,pIncreace,decrease,pDecrease;
        BigDecimal h = new BigDecimal("100");
        //https://www.educative.io/answers/how-to-compare-two-bigdecimals-in-java
        //https://www.investopedia.com/terms/p/percentage-change.asp
        if(getInvestedCapital().compareTo(getCurrentValue()) < 0) {
            // increase calc
            increase = getCurrentValue().subtract(getInvestedCapital());
            pIncreace = increase.divide(getInvestedCapital()).multiply(h);
            return pIncreace;
        }else{
            // decrease calc
            decrease = getInvestedCapital().subtract(getCurrentValue());
            pDecrease = decrease.divide(getInvestedCapital()).multiply(h);
            return pDecrease;

        }


    };


    private BigDecimal getInvestedCapital(){
        BigDecimal investedCapital = new BigDecimal("0");

        for(Transaction t : service.findAllTransactions("")){
            investedCapital = investedCapital.add(t.getSrcAmount());
            System.out.println("Src Amount " + t.getSrcAmount());
            System.out.println("Src Amount " + investedCapital);

        };


        System.out.println("Invested Capital " + investedCapital);


        return investedCapital;

    };

    private BigDecimal getCurrentValue() {
        BigDecimal currentValue = new BigDecimal("0");
        String s = new String("Test");
        for (Transaction t : service.findAllTransactions("")) {

            System.out.println(service.findAllCryptoAssets(t.getDestAsset().getFullname()).size());
            System.out.println(service.findAllCryptoAssets(t.getDestAsset().getFullname()).indexOf(t.getDestAsset()));

            System.out.println("Dest Asset " + service.findAllCryptoAssets(t.getDestAsset().getFullname()).get(0).getFullname());

            // get dest asset fullname and search for it in the repo get the first object of the list - assuming there is one - none will throw error
            CryptoAsset ca = service.findAllCryptoAssets(t.getDestAsset().getFullname()).get(0);

            System.out.println("Dest Amount " + s);

            // multiply the destination amount with the current value of the crypto asset
            currentValue = currentValue.add(t.getDestAmount().multiply(ca.getCurrentValue()));

            System.out.println("current Value " + currentValue);
        };

        return currentValue;

    };


    private Component createHighlight(String title, String value /*,String percentage*/) {
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
*/
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