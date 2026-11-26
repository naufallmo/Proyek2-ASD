public class Course {
    public enum Paritas {
        GANJIL, GENAP, KEDUANYA
    }

    private String kode;
    private String nama;
    private int sks;
    private int semester;
    private Paritas paritas;

    public Course(String kode, String nama, int sks, int semester, Paritas paritas) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.semester = semester;
        this.paritas = paritas;
    }

    public static Course fromCsvLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] data = line.trim().split(";");

        if (data[0].trim().equalsIgnoreCase("Kode")) {
            return null;
        }

        if (data.length < 5) {
            throw new IllegalArgumentException("Data mata kuliah tidak valid: " + line);
        }

        String kode = data[0].trim();
        String nama = data[1].trim();
        int sks = Integer.parseInt(data[2].trim());
        int semester = Integer.parseInt(data[3].trim());
        Paritas paritas = parseParitas(data[4].trim());

        return new Course(kode, nama, sks, semester, paritas);
    }

    private static Paritas parseParitas(String data) {
        if (data.equalsIgnoreCase("Ganjil")) {
            return Paritas.GANJIL;
        } else if (data.equalsIgnoreCase("Genap")) {
            return Paritas.GENAP;
        } else if (data.equalsIgnoreCase("Keduanya")) {
            return Paritas.KEDUANYA;
        }
        throw new IllegalArgumentException("Paritas tidak dikenal: " + data);
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getSks() {
        return sks;
    }

    public int getSemester() {
        return semester;
    }

    public Paritas getParitas() {
        return paritas;
    }

    public boolean isOfferedIn(boolean semesterGanjil) {
        if (paritas == Paritas.KEDUANYA) {
            return true;
        } else if (paritas == Paritas.GANJIL && semesterGanjil) {
            return true;
        } else if (paritas == Paritas.GENAP && !semesterGanjil) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return kode + " " + nama + " " + sks + " SKS";
    }
}