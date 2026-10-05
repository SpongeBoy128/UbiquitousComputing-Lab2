# Ubiquitous Computing - Lab 2

This repository contains two unique Android applications implemented in Java, expanding on the core layout form concepts from Lab 1.

## Repository Contents

* **FormAppExplicit**: Demonstrates the use of an **Explicit Intent** to pass validated user text data (`nameInput`) from the main registration screen onto a dedicated secondary screen (`SecondActivity`).
* **FormAppImplicit**: Demonstrates the use of an **Implicit Intent** using `Intent.ACTION_SENDTO` to generate a random 4-digit verification code and securely hand it off to an external email system. Account authorization is verified directly on-screen using custom input validation fields.

## Development Environment
* **Language**: Java (JDK 11)
* **IDE**: Android Studio
* **Tested Form Factor**: Pixel 7 (Stable API 34 Platform)