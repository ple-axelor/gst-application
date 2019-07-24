package com.axelor.gst.web;

import java.util.List;

import com.axelor.gst.db.Country;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.service.InvoiceService;
import com.axelor.gst.service.SequenceService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class InvoiceController {

  @Inject private InvoiceService service;
  @Inject private SequenceService sequenceService;

  public void setPartyData(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setPartyData(invoice);
    response.setValue("partyContact", invoice.getPartyContact());
    response.setValue("invoiceAddress", invoice.getInvoiceAddress());
    response.setValue("shippingAddress", invoice.getShippingAddress());
  }

  public void setShippingAddress(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setShippingAddress(invoice);
    response.setValue("shippingAddress", invoice.getShippingAddress());
  }

  public void setDetails(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setDetails(invoice);
    response.setValue("netAmount", invoice.getNetAmount());
    response.setValue("netIgst", invoice.getNetIgst());
    response.setValue("netSgst", invoice.getNetSgst());
    response.setValue("netCgst", invoice.getNetCgst());
    response.setValue("grossAmount", invoice.getGrossAmount());
  }

  public void getReference(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    String model = request.getModel();
    if (invoice.getReference() == null) {
      String reference = sequenceService.getReference(model);
      if (reference == null) {
        response.setError("Generate Sequence");
      } else {
        response.setValue("reference", reference);
      }
    }
  }
}
