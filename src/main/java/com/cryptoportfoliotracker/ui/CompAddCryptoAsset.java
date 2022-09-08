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

    public CompAddCryptoAsset(List<CryptoAsset> AssetList, List<Platform> PlatformList) {
        addClassName("Asset-list");
        binder.bindInstanceFields(this);

        // laden der Listen für die Dropdowns
        platform.setItems(PlatformList);

        // Anzeigename defineieren welche in der Dropdown angezeigt werden
        platform.setItemLabelGenerator(Platform::getName);

        add(fullname,
            shortname,
            platform,
            currentValueFiat,
            createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);


        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, cryptoAsset)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);

    }

    public void setCryptoAsset(CryptoAsset cryptoAsset) {
        this.cryptoAsset = cryptoAsset;
        binder.readBean(cryptoAsset);
    }

    private void validateAndSave() {
        try {

            binder.writeBean(cryptoAsset);

            fireEvent(new SaveEvent(this, cryptoAsset));

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class CompAddAssetEvent extends ComponentEvent<CompAddCryptoAsset> {
        private CryptoAsset cryptoAsset;

        protected CompAddAssetEvent(CompAddCryptoAsset source, CryptoAsset cryptoAsset) {
            super(source, false);
            this.cryptoAsset = cryptoAsset;
        }

        public CryptoAsset getCryptoAsset() {
            return cryptoAsset;
        }
    }

    public static class SaveEvent extends CompAddAssetEvent {
        SaveEvent(CompAddCryptoAsset source, CryptoAsset cryptoAsset) {
            super(source, cryptoAsset);
        }
    }

    public static class DeleteEvent extends CompAddAssetEvent {
        DeleteEvent(CompAddCryptoAsset source, CryptoAsset cryptoAsset) {
            super(source, cryptoAsset);
        }

    }

    public static class CloseEvent extends CompAddAssetEvent {
        CloseEvent(CompAddCryptoAsset source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {


        return getEventBus().addListener(eventType, listener);
    }


}