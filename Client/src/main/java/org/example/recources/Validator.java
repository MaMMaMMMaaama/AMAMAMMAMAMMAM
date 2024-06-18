package org.example.recources;

import org.example.exceeption.NoElementException;
import org.example.exceeption.WrongArgumentException;
import org.example.recources.generator.IdGenerator;

public class Validator {
    public static boolean checkId(String line) throws WrongArgumentException, NoElementException {
        if (line.isEmpty()) {
            return false;
        } else {
            try {
                if (IdGenerator.idInList(Integer.parseInt(line))) {
                    return true;
                } else throw new NoElementException();
            } catch (NumberFormatException e) {
                throw new WrongArgumentException();
            } catch (NoElementException e) {
                throw new NoElementException();
            }
        }
    }

    public static boolean checkName(String line) throws WrongArgumentException {
        if (!line.isEmpty()) {
            return true;
        }
        throw new WrongArgumentException();
    }

    public static boolean checkXCoordinates(String line) throws WrongArgumentException {
        if (line.isEmpty()) {
            return true;
        } else {
            try {
                Float.parseFloat(line);
                return true;
            } catch (NumberFormatException e) {
                throw new WrongArgumentException();
            }
        }
    }

    public static boolean checkYCoordinates(String line) throws WrongArgumentException {
        if (line.isEmpty()) {
            return true;
        } else {
            try {
                if (Double.parseDouble(line) > -885) {
                    return true;
                }
                throw new WrongArgumentException();
            } catch (NumberFormatException e) {
                throw new WrongArgumentException();
            }
        }
    }

    public static boolean checkSalary(String line) throws WrongArgumentException {
        if (!line.isEmpty()) {
            try {
                if (Integer.parseInt(line) > 0) {
                    return true;
                }
                throw new WrongArgumentException();
            } catch (NumberFormatException e) {
                throw new WrongArgumentException();
            }
        } else {
            throw new WrongArgumentException();
        }
    }

    public static boolean checkPassportId(String line) throws WrongArgumentException {
        if (line.isEmpty()) {
            return true;
        } else {
            if (line.length() >= 5 && line.length() <= 35) {
                return true;
            }
            throw new WrongArgumentException();
        }
    }
}
