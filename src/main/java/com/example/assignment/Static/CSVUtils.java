package com.example.assignment.Static;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVUtils {
    public static final Map<String, String[]> headersMap = new HashMap<>();

    static {
        headersMap.put("add_student_form.csv", new String[]{
                "student_id", "first_name", "last_name", "gender", "ph_number", "email", "faculty", "password"
        });
        headersMap.put("add_teacher_form.csv", new String[]{
                "teacher_id", "name", "gender", "ph_number", "email", "password"
        });
        headersMap.put("add_staff_form.csv", new String[]{
                "staff_id", "name", "gender", "ph_number", "email", "password"
        });
        headersMap.put("add_activities_form.csv", new String[]{
                "activity_id", "activity_type", "activity_date"
        });
        headersMap.put("add_admission_form.csv", new String[]{
                "student_id", "name", "email", "application_date", "status"
        });
        headersMap.put("add_details.csv", new String[]{
                "student_id", "student_name", "book_name", "book_number", "book_status"
        });
    }

    // CSV Methods (requires opencsv)

    /**
     * Creates directories and files if they do not exist.
     */
    public static void initializeFiles() {
        final String DIRECTORY_NAME = "csv_files";
        try {
            Path directoryPath = Paths.get(DIRECTORY_NAME);

//            if directory doesn't exist, create one
            if (!Files.exists(directoryPath)) {
                System.out.println("Creating directory...");
                Files.createDirectory(directoryPath);
            }

            headersMap.forEach((key, value) -> {
                Path filePath = Paths.get(DIRECTORY_NAME, key);
//            if file doesn't exist, create one
                if (!Files.exists(filePath)) {
                    try {
                        boolean isFileWritten = writeCSV(filePath.toString(), value, new String[]{});
                        if (isFileWritten) {
                            System.out.printf("Created %s with headers successfully.\n", filePath);
                        }
                    } catch (IOException e) {
                        System.out.printf("Failed to write to file %s\n.", filePath);
                    }
                } else {
                    System.out.printf("File %s already exists. Skipping.\n", filePath);
                }
            });

        } catch (IOException e) {
            System.out.println("Error initializing CSV files.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new CSV file and writes to it.
     *
     * @param filename path of the CSV file
     * @param headers  headers (column names) for the CSV file
     * @param data     records for the CSV file
     * @return boolean {@code true} if file is written successfully {@code false} otherwise
     * @throws IOException when filename not found
     */
    public static boolean writeCSV(String filename, String[] headers, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            writer.writeNext(headers);

            for (String[] record : data) {
                writer.writeNext(record);
            }

            return true;
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }


    /**
     * Creates a new CSV file and writes to it.
     *
     * @param filename path of the CSV file
     * @param headers  headers (column names) for the CSV file
     * @param record   a single records for the CSV file
     * @return {@code boolean} {@code true} if file is written successfully {@code false} otherwise
     * @throws IOException when filename not found
     */
    public static boolean writeCSV(String filename, String[] headers, String[] record) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            writer.writeNext(headers);
            writer.writeNext(record);

            return true;
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }

    /**
     * Appends a list of records to an existing CSV file.
     *
     * @param filename path of the CSV file
     * @param data     records for the CSV file
     * @return boolean {@code true} if file is written successfully {@code false} otherwise
     */
    public static boolean appendCSV(String filename, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename, true))) {
            for (String[] record : data) {
                writer.writeNext(record);
            }

            return true;
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }


    /**
     * Appends a single record to an existing CSV file.
     *
     * @param filename path of the CSV file
     * @param record   a single record for the CSV file
     * @return boolean {@code true} if file is written successfully {@code false} otherwise
     */
    public static boolean appendCSV(String filename, String[] record) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename, true))) {
            writer.writeNext(record);

            return true;
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }

    /**
     * Reads a CSV file and returns the content as a List of the generic Type
     *
     * @param filename path of the CSV file
     * @param headers  headers (column names) for the CSV file
     * @param clazz    the class of the {@code T} generic
     * @param <T>      Type
     * @return {@code List<T>}
     * @throws IOException when filename not found
     */

//    Honestly, this is such a lame method. It depends entirely on the CSV
//    headers and the positional arguments of the constructor of  T?
//    There's probably a better way to do this.
    public static <T> List<T> readCSV(String filename, String[] headers, Class<T> clazz) throws IOException {
        ArrayList<T> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] nextLine;
            // exhaust the first line for headers
            if ((nextLine = reader.readNext()) != null) {
                if (!java.util.Arrays.equals(nextLine, headers)) {
                    throw new IOException("CSV headers do not match the provided headers.");
                } else {
//                    exhaust the second one because there's always
//                    an empty line after the headers
                    reader.readNext();
                }
            }

//            So, I guess what this *line* of code really does is just
//            find the constructor class of T corresponding to the header
//            (its length and sequence? - and types?)
            Constructor<T> constructor = clazz.getConstructor(
                    Arrays.stream(headers)
//                            for each header (h), map it to String.class type
//                            this assumes all CSV fields are of type `String`
                            .map(h -> String.class)
//                            Collect the mapped `String.class` into an array?
                            .toArray(Class[]::new)
            );

            while ((nextLine = reader.readNext()) != null) {
//                Convert the `String[]` type nextLine array to `Object[]`
//                and of course, instantiate an object
                T object = constructor.newInstance((Object[]) nextLine);
                records.add(object);
            }
        } catch (IOException | CsvValidationException exc) {
            System.out.println(exc.getMessage());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            System.out.println(e.getLocalizedMessage());
            e.getCause();
        }

        return records;
    }
}
