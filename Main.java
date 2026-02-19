import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
public static ArrayList<Lecture> rLectures(String fileName) {
ArrayList<Lecture> lectures = new ArrayList<Lecture>();
File inputFile = new File(fileName);
boolean rLabs = false;
try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
String line;
while ((line = br.readLine()) != null) {
String[] fields = line.trim().split(",");
if (rLabs) {
if (fields.length != 2) {
rLabs = false;
} else {
int crn = Integer.parseInt(fields[0].trim());
String roomNumber = fields[1].trim();
lectures.get(lectures.size() - 1).addLab(crn, roomNumber);
continue;
}
}
int crn = Integer.parseInt(fields[0].trim());
String prefix = fields[1].trim();
String title = fields[2].trim();
String type = fields[3].trim();
if (fields[4].trim().equalsIgnoreCase("ONLINE")) {
lectures.add(new Lecture(crn, prefix, title, type));
} else {
String buildingCode = fields[4].trim();
String roomNumber = fields[5].trim();
boolean hasLabs = false;
if (fields[6].trim().equalsIgnoreCase("YES")) {
hasLabs = true;
rLabs = true;
}
lectures.add(new Lecture(crn, prefix, title, type,
buildingCode, roomNumber, hasLabs));
}
}
} catch (IOException | IndexOutOfBoundsException ex) {
ex.printStackTrace();
}
return lectures;
}
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
ArrayList<Lecture> lectures = rLectures("lec.txt");
int numOnline = 0;
for (Lecture lecture : lectures) {
if (lecture.isOnline()) {
numOnline++;
}
}
System.out.printf("There are %d online lectures offered.\n", numOnline);
System.out.print("Enter Room Number: ");
String roomNumber = input.nextLine();
for (Lecture lecture : lectures) {
if (!lecture.isOnline()) {
if (lecture.getRoomNumber().equalsIgnoreCase(roomNumber)) {
System.out.println(lecture.getCRN());
}
if (lecture.hasLabs()) {
ArrayList<Lab> labs = lecture.getLabs();
for (Lab lab : labs) {
if (lab.getRoomNumber().equalsIgnoreCase(roomNumber)) {
System.out.println(lab.getCRN());
}
}
}
}
}
input.close();
String outputFileName = "lecturesOnly.txt";
try {
PrintWriter w = new PrintWriter(outputFileName);
w.write("Online Lectures\n");
for (Lecture lecture : lectures) {
if (lecture.isOnline()) {
w.write(lecture.toString() + "\n");
}
}
w.write("\n\nLectures With No Labs\n");
for (Lecture lecture : lectures) {
if (!lecture.isOnline() && !lecture.hasLabs()) {
w.write(lecture.toString() + "\n");
}
}
w.close();
System.out.printf("%s is created.\n", outputFileName);
} catch (FileNotFoundException ex) {
ex.printStackTrace();
}
}
}
class Lab {
private int crn;
private String roomNumber;
public Lab(int crn, String roomNumber) {
this.crn = crn;
this.roomNumber = roomNumber;
}
public int getCRN() {
return crn;
}
public String getRoomNumber() {
return roomNumber;
}
}
class Lecture {
private int crn;
private String prefix;
private String title;
private String type;
private String buildingCode;
private String roomNumber;
private boolean isOnline;
private boolean hasLabs;
private ArrayList<Lab> labs;
public Lecture(int crn, String prefix, String title, String type) {
this(crn, prefix, title, type, null, null, false);
this.isOnline = true;
}
public Lecture(int crn, String prefix, String title, String type, String
buildingCode, String roomNumber,
boolean hasLabs) {
this.crn = crn;
this.prefix = prefix;
this.title = title;
this.type = type;
this.buildingCode = buildingCode;
this.roomNumber = roomNumber;
this.hasLabs = hasLabs;
if (this.hasLabs) {
labs = new ArrayList<Lab>();
}
}
public void addLab(int crn, String roomNumber) {
labs.add(new Lab(crn, roomNumber));
}
public boolean isOnline() {
return isOnline;
}
public boolean hasLabs() {
return hasLabs;
}
public String getRoomNumber() {
return roomNumber;
}
public ArrayList<Lab> getLabs() {
return labs;
}
public int getCRN() {
return crn;
}
@Override
public String toString() {
if (isOnline()) {
return String.format("%d, %s, %s, %s, Online",
crn, prefix, title, type);
} else {
return String.format("%d, %s, %s, %s, %s, %s, %s",
crn, prefix, title, type, buildingCode, roomNumber, hasLabs() ?
"Yes" : "No");
}
}
}
