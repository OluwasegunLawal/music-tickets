package musicals.utils;

import musicals.models.Order;

/**
 * Utility class responsible for generating receipts from Order objects
 * in different formats (text and PDF).
 */
public class ReceiptGenerator {
    /** The order for which receipts will be generated */
    private Order order;
    
    /**
     * Constructs a new ReceiptGenerator for the specified order
     * @param order The order to generate receipts for
     */
    public ReceiptGenerator(Order order) {
        this.order = order;
    }
    
    /**
     * Generates a formatted text receipt for the order
     * @return String containing the formatted receipt text
     */
    public String generateTextReceipt() {
        StringBuilder receipt = new StringBuilder();
        // Generate formatted text receipt
        return receipt.toString();
    }
    
    /**
     * Generates a PDF receipt and saves it to the specified file path
     * @param filePath The path where the PDF file should be saved
     */
    public void generatePDFReceipt(String filePath) {
        // Generate PDF receipt using a library like iText or PDFBox
    }
} 