
package com.cryptoportfoliotracker.ui;
import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import org.hibernate.event.spi.DeleteEvent;


import java.util.Collection;
import java.util.List;

public class CompAddTransaction extends FormLayout {

  Transaction transaction;

  DateTimePicker dateTimePicker = new DateTimePicker();

  ComboBox<CryptoAsset> SrcAsset = new ComboBox<>("Source Asset");
  ComboBox<CryptoAsset> DstAsset = new ComboBox<>("Destination Asset");
  IntegerField SrcAmount = new IntegerField("Source Amount");
  IntegerField DstAmount = new IntegerField("Destination Amount");

  ComboBox<Platform> SrcPlatform = new ComboBox<>("Source Platform");
  ComboBox<Platform> DstPlatform = new ComboBox<>("Destination Platform");
  IntegerField Fee = new IntegerField("Fee");
  ComboBox<CryptoAsset> FeeAsset = new ComboBox<>("Fee Asset");
  TextField Notes = new TextField("Notes");

  Binder<Transaction> binder = new BeanValidationBinder<>(Transaction.class);




  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public CompAddTransaction(List<CryptoAsset> CryptoAssetList, List<Platform> PlatformList, List<Asset> AssetList) {
    addClassName("contact-form");
    //binder.bindInstanceFields(this);

    // umwandeln von Repo was ein ArrayList ist zu Interface List und befüllen der Dropdown listen
    SrcAsset.setItems(CryptoAssetList);
    DstAsset.setItems(CryptoAssetList);
    SrcPlatform.setItems(PlatformList);
    DstPlatform.setItems(PlatformList);
    FeeAsset.setItems(CryptoAssetList);

    // Anzeigename defineieren welche in der Dropdown angezeigt werden??
    SrcAsset.setItemLabelGenerator(CryptoAsset::getShortname);
    DstAsset.setItemLabelGenerator(CryptoAsset::getShortname);
    SrcPlatform.setItemLabelGenerator(Platform::getName);
    DstPlatform.setItemLabelGenerator(Platform::getName);
    FeeAsset.setItemLabelGenerator(Asset::getShortname);

    add(dateTimePicker,
        SrcAsset,
        DstAsset,
        SrcPlatform,
        DstPlatform,
        Fee,
        FeeAsset,
        Notes,
        createButtonsLayout());
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    /*
    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, transaction)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));
    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
*/
    return new HorizontalLayout(save, delete, close);


  }
/*
  public void setContact(Transaction transaction) {
    this.transaction = transaction;
    binder.readBean(transaction);
  }


  private void validateAndSave() {
    try {
      binder.writeBean(transaction);


      fireEvent(new SaveEvent(this, transaction));


    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }
  // Events
  public static abstract class CompAddTransactionEvent extends ComponentEvent<CompAddTransaction> {
    private Transaction transaction;

    protected CompAddTransactionEvent(CompAddTransaction source, Transaction transaction) {


      super(source, false);
      this.transaction = transaction;
    }

    public Transaction getTransaction() {
      return transaction;
    }
  }

  public static class SaveEvent extends CompAddTransactionEvent {
    SaveEvent(CompAddTransaction source, Transaction transaction) {
      super(source, transaction);
    }
  }

  public static class DeleteEvent extends CompAddTransactionEvent {
    DeleteEvent(CompAddTransaction source, Transaction transaction) {
      super(source, transaction);
    }

  }

  public static class CloseEvent extends CompAddTransactionEvent {
    CloseEvent(CompAddTransaction source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {


    return getEventBus().addListener(eventType, listener);
  }*/


}