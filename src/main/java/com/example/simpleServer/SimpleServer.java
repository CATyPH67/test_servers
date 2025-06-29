package com.example.simpleServer;

import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Блокирует до подключения клиента
                System.out.println("New client connected");

                // Создаём новый поток для обработки клиента
                new Thread(() -> handleClient(socket)).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Читаем первую строку HTTP-запроса (например: GET / HTTP/1.1)
            String requestLine = reader.readLine();
            System.out.println("Request: " + requestLine);

            // Подготовка ответа
            String responseBody = "Hello from server!";
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain; charset=utf-8");
            writer.println("Content-Length: " + responseBody.length());
            writer.println(); // Пустая строка между заголовками и телом
            writer.println(responseBody);

        } catch (IOException ex) {
            System.out.println("Client handling error: " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Failed to close socket: " + e.getMessage());
            }
        }
    }
}