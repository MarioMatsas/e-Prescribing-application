package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

public interface PrescriptionCreationView {
    /**
     * Δημιουργεί και γεμίζει το spinner για τις δραστικές ουσίες.
     */
    void populateActiveSubSpinner();
    /**
     * Αρχικοποιεί το spinner της οθόνης, για το Form.
     * Κάθε Form έχει δική του μονάδα μέτρησης (Unit) και
     * για αυτό υπάρχει switch για να κάνει έλεγχο κάθε φορά.
     */
    void setupFormSpinner();
    /**
     * Κοιτά αν μπορεί να δημιουργηθεί η συνταγή(μέσω του presenter)
     * και αν μπορεί, τερματίζει το activity.
     */
    void createPrescription();
    /**
     * Καλεί τον presenter, μέσω του viewModel, για να προσθέσει την γραμμή στην συνταγή.
     */
    void addLine();
    /**
     * Καθαρίζει τα πεδία συμπλήρωσης, για να μήν μπερδευτεί ο χρήστης.
     */
    void clearFields();
    /**
     * Ορίζει στο errorMessage μήνυμα,
     * το περιεχόμενο του message.
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showError(String message);

}
