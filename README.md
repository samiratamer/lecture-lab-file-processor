# Lecture & Lab File Processor

A Java application that reads course data from a structured text file, filters and queries lectures and labs, and generates a categorized output report.

## Features
- Parses a CSV-formatted input file (lec.txt) containing lecture and lab records
- Counts and displays the number of online lectures
- Accepts a room number as input and prints all matching CRNs for lectures and labs
- Generates a lecturesOnly.txt report with two sections:
  - Online lectures
  - In-person lectures with no associated labs

## Concepts Used
- File I/O with BufferedReader and PrintWriter
- CSV parsing and data validation
- Object-oriented design with Lecture and Lab classes
- ArrayList filtering and iteration
- Constructor overloading
- toString() override for formatted output

## How to Run
1. Place your `lec.txt` input file in the same directory as the program
2. Compile and run:
javac Main.java
java Main
3. Enter a room number when prompted
4. A `lecturesOnly.txt` report will be generated in the same directory

## Input File Format
CRN, Prefix, Title, Type, ONLINE
CRN, Prefix, Title, Type, BuildingCode, RoomNumber, YES/NO
LabCRN, LabRoomNumber

## Course
Object-Oriented Programming (COP 3330) – University of Central Florida
