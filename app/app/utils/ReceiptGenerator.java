package app.utils;

import app.models.Order;

public class ReceiptGenerator {
    private Order order;
    
    public ReceiptGenerator(Order order) {
        this.order = order;
    }
    
    public String generateTextReceipt() {
        StringBuilder receipt = new StringBuilder();
        // Generate formatted text receipt
        return receipt.toString();
    }
    
    public void generatePDFReceipt(String filePath) {
        // Generate PDF receipt using a library like iText or PDFBox
    }
} 