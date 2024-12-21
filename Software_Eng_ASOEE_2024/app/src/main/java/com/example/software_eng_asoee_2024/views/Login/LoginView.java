package com.example.software_eng_asoee_2024.views.Login;

public interface LoginView {
    void showSearchDialog(String bookTitle, String authorName);

    void showError(String msg);

    void showStatus(String msg);
}
