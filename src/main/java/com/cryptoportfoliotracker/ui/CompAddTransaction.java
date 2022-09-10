package com.cryptoportfoliotracker.ui;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
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
 * @see TransactionView
 */
public class CompAddTransaction extends FormLayout {
    Transaction transaction;
    DateTimePicker dateAndTime = new DateTimePicker();
    ComboBox<Asset> srcAsset = new ComboBox<>("Source Asset");
    ComboBox<Asset> destAsset = new ComboBox<>("Destination Asset");
    BigDecimalField srcAmount = new BigDecimalField("Source Amount");
    BigDecimalField destAmount = new BigDecimalField("Destination Amount");
    ComboBox<Platform> srcPlatform = new ComboBox<>("Source Platform");
    ComboBox<Platform> destPlatform = new ComboBox<>("Destination Platform");
    BigDecimalField fee = new BigDecimalField("Fee");
    ComboBox<Asset> feeAsset = new ComboBox<>("Fee Asset");
    TextField notes = new TextField("Notes");
    Binder<Transaction> binder = new BeanValidationBinder<>(Transaction.class);
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    /**
     * Creates a new component add transaction instance
     *
     * @param platformList of platforms
     * @param assetList    of all assets
     */
    public CompAddTransaction(List<Asset> assetList, List<Platform> platformList) {
        addClassName("transaction-list");
        binder.bindInstanceFields(this);

        /** Loading the dropdowns */
        destPlatform.setItems(platformList);
        srcPlatform.setItems(platformList);
        destPlatform.setItems(platformList);
        feeAsset.setItems(assetList);

        /** Define the labels of the dropdown list */
        srcPlatform.setItemLabelGenerator(Platform::getName);
        destPlatform.setItemLabelGenerator(Platform::getName);
        feeAsset.setItemLabelGenerator(Asset::getShortNameAndPlatform);

        /** Listener on the field when changed load the dropdown for the assets but only assets from the selected platform */
        destPlatform.addValueChangeListener(event -> fireEvent(new DestPlatformEvent(this, destPlatform.getValue(), destAsset)));
        srcPlatform.addValueChangeListener(event -> fireEvent(new SrcPlatformEvent(this, srcPlatform.getValue(), srcAsset)));

        /** Adds all the fields to the component */
        add(dateAndTime, srcPlatform, srcAsset, srcAmount, destPlatform, destAsset, destAmount, fee, feeAsset, notes, createButtonsLayout());
    }

    /**
     * Creates the horizontal layout for the buttons
     *
     * @see #CompAddTransaction(List, List)
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, transaction)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);

    }

    /**
     * Load selected transaction to the component / editor
     *
     * @param transaction to be displayed
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        binder.readBean(transaction);
        binder.readBean(transaction);
    }

    /**
     * Reads the transaction from to ui and writes it to the bean and saves it via the event
     *
     * @see CompAddCryptoAsset.SaveEvent
     */
    private void validateAndSave() {
        try {

            binder.writeBean(transaction);

            fireEvent(new SaveEvent(this, transaction));

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {


        return getEventBus().addListener(eventType, listener);
    }

    /***
     * Superclass for CRUD events
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction
     */
    public static abstract class CompAddTransactionEvent extends ComponentEvent<CompAddTransaction> {
        private final Transaction transaction;

        /**
         * Creates a new component add transaction instance
         *
         * @param source      of the event
         * @param transaction
         */
        protected CompAddTransactionEvent(CompAddTransaction source, Transaction transaction) {
            super(source, false);
            this.transaction = transaction;
        }

        /**
         * Getter for the transaction of the event
         *
         * @return cryptoAsset
         */
        public Transaction getTransaction() {
            return transaction;
        }
    }

    /***
     * Class for event of saving the object
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction.CompAddTransactionEvent
     */
    public static class SaveEvent extends CompAddTransactionEvent {
        /**
         * Creates a new component event to save the crypto asset
         *
         * @param source      of the event
         * @param transaction to be saved
         */
        SaveEvent(CompAddTransaction source, Transaction transaction) {
            super(source, transaction);
        }
    }

    /***
     * Class for event of deletion of the object
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction.CompAddTransactionEvent
     */
    public static class DeleteEvent extends CompAddTransactionEvent {
        DeleteEvent(CompAddTransaction source, Transaction transaction) {
            super(source, transaction);
        }

    }

    /***
     * Class for event to close the editor
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction.CompAddTransactionEvent
     */
    public static class CloseEvent extends CompAddTransactionEvent {
        /**
         * Creates a new component event to close the editor
         *
         * @param source of the event
         */
        CloseEvent(CompAddTransaction source) {
            super(source, null);
        }
    }

    /***
     * Superclass for platform dropdown list events
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction
     */
    public static abstract class CompAddTransactionPlatformEvent extends ComponentEvent<CompAddTransaction> {
        private final Platform platform;
        private final ComboBox<Asset> asset;

        /**
         * Creates a new component add transaction instance
         *
         * @param source   of the event
         * @param platform which has been selected
         * @param asset    field of the view
         */
        protected CompAddTransactionPlatformEvent(CompAddTransaction source, Platform platform, ComboBox<Asset> asset) {
            super(source, false);
            this.platform = platform;
            this.asset = asset;
        }

        /**
         * Getter for the platform
         *
         * @return platform
         */
        public Platform getPlatform() {
            return platform;
        }

        /**
         * Getter for the platform
         *
         * @return asset
         */
        public ComboBox<Asset> getAsset() {
            return asset;
        }


    }

    /***
     * Class for event when destination platform value has changed
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction.CompAddTransactionPlatformEvent
     */
    public static class DestPlatformEvent extends CompAddTransactionPlatformEvent {
        /**
         * Creates a new component event so that the transaction view can load the assets of the platform
         *
         * @param source    of the event
         * @param platform
         * @param destAsset
         */
        DestPlatformEvent(CompAddTransaction source, Platform platform, ComboBox<Asset> destAsset) {
            super(source, platform, destAsset);
        }

    }

    /***
     * Class for event when source platform value has changed
     *
     * @author Noah Li Wan Po
     * @version 1.0
     * @see CompAddTransaction.CompAddTransactionPlatformEvent
     */
    public static class SrcPlatformEvent extends CompAddTransactionPlatformEvent {
        /**
         * Creates a new component event so that the transaction view can load the assets of the platform
         *
         * @param source   of the event
         * @param platform
         * @param srcAsset
         */
        SrcPlatformEvent(CompAddTransaction source, Platform platform, ComboBox<Asset> srcAsset) {
            super(source, platform, srcAsset);
        }

    }


}