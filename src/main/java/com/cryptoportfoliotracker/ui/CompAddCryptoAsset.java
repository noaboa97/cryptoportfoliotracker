package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

/***
 * Component / editor to create, update and delete crypto assets in the ui
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see CryptoAssetView
 */
public class CompAddCryptoAsset extends FormLayout {
    CryptoAsset cryptoAsset;
    TextField fullname = new TextField("Name");
    TextField shortname = new TextField("Short");
    ComboBox<Platform> platform = new ComboBox<>("Platform");
    TextField currentValueFiat = new TextField("CHF");

    BigDecimalField investedCapitalFiat = new BigDecimalField("Invested capital");
    BigDecimalField investedCapitalCrypto = new BigDecimalField("Invested capital");
    BigDecimalField currentBalanceCrypto = new BigDecimalField("Current balance");
    BigDecimalField currentBalanceFiat = new BigDecimalField("Current balance");

    Binder<CryptoAsset> binder = new BeanValidationBinder<>(CryptoAsset.class);
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    /**
     * Creates a new component add crypto asset instance
     *
     * @param PlatformList of platforms
     */
    public CompAddCryptoAsset(List<Platform> PlatformList) {
        addClassName("Asset-list");

        /** binding field from ui to entities */
        binder.bindInstanceFields(this);

        /** Loads the dropdown list for the platform selection */
        platform.setItems(PlatformList);

        /** Define the labels of the dropdown list */
        platform.setItemLabelGenerator(Platform::getName);

        /** Adds all the fields to the component */
        add(fullname, shortname, platform, currentValueFiat, createButtonsLayout());
    }

    /**
     * Creates the horizontal layout for the buttons
     *
     * @see #CompAddCryptoAsset(List)
     */
    private HorizontalLayout createButtonsLayout() {
        /** Add the theme to the buttons */
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        /** Shortcurt keys which can be used on the keyboard */
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        /** Adds a click listener to the button which will fire the event to save when button is clicked */
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, cryptoAsset)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);

    }

    /**
     * Load selected crypto asset to the component / editor
     *
     * @param cryptoAsset to be displayed
     */
    public void setCryptoAsset(CryptoAsset cryptoAsset) {
        this.cryptoAsset = cryptoAsset;
        binder.readBean(cryptoAsset);
    }

    /**
     * Reads the crypto asset from to ui and writes it to the bean and saves it via the event
     *
     * @see SaveEvent
     */
    private void validateAndSave() {
        try {

            binder.writeBean(cryptoAsset);

            fireEvent(new SaveEvent(this, cryptoAsset));

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {


        return getEventBus().addListener(eventType, listener);
    }

    /***
     * Superclass for events
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddCryptoAsset
     */
    public static abstract class CompAddAssetEvent extends ComponentEvent<CompAddCryptoAsset> {
        private final CryptoAsset cryptoAsset;

        /**
         * Creates a new component add crypto asset instance
         *
         * @param source      of the event
         * @param cryptoAsset
         */
        protected CompAddAssetEvent(CompAddCryptoAsset source, CryptoAsset cryptoAsset) {
            super(source, false);
            this.cryptoAsset = cryptoAsset;
        }

        /**
         * Getter for the crypto asset of the event
         *
         * @return cryptoAsset
         */
        public CryptoAsset getCryptoAsset() {
            return cryptoAsset;
        }
    }

    /***
     * Class for event of saving the object
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddAssetEvent
     */
    public static class SaveEvent extends CompAddAssetEvent {

        /**
         * Creates a new component event to save the crypto asset
         *
         * @param source      of the event
         * @param cryptoAsset to be saved
         */
        SaveEvent(CompAddCryptoAsset source, CryptoAsset cryptoAsset) {
            super(source, cryptoAsset);
        }
    }

    /***
     * Class for event of deletion of the object
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddAssetEvent
     */
    public static class DeleteEvent extends CompAddAssetEvent {
        /**
         * Creates a new component event to delete the crypto asset
         *
         * @param source      of the event
         * @param cryptoAsset to be saved
         */
        DeleteEvent(CompAddCryptoAsset source, CryptoAsset cryptoAsset) {
            super(source, cryptoAsset);
        }

    }

    /***
     * Class for event to close the editor
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddAssetEvent
     */
    public static class CloseEvent extends CompAddAssetEvent {
        /**
         * Creates a new component event to close the editor
         *
         * @param source of the event
         */
        CloseEvent(CompAddCryptoAsset source) {
            super(source, null);
        }
    }


}