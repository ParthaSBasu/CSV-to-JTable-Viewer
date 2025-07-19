# CSV to JTable Viewer (Java Swing)

A Java Swing application that reads a CSV file and displays the data in a scrollable JTable.  
It also builds a `HashMap<String, ArrayList<String>>` from the CSV file, where each key is a column header and the corresponding value is a list of all values under that column.

---

## ✨ Features

- ✅ Reads CSV file (row-wise)
- ✅ Displays the data using `JTable` with proper fonts, borders, and scrollbars
- ✅ Builds a column-wise `HashMap` with headers as keys
- ✅ Modular code: file reading, table display, and data processing separated
- ✅ Exit confirmation dialog on window close
- ✅ Graceful handling of file not found

---

## Requirements

- Java 8 or higher
- Works on any OS with Java installed

## Usage

1. Clone this repo
2. Compile the Java file:
   javac CsvFilesDemo2.java
