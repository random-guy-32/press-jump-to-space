package pressjumptospace.util;

import pressjumptospace.entity.EntitySpawner;
import pressjumptospace.level.Chunk;
import pressjumptospace.level.Level;
import pressjumptospace.level.Spawnpoint;

import java.io.*;


public class FileManager {
    public static final String ROOT = System.getProperty("user.dir") + FileManager.SAVESLOCATION;
    public static final String FILEEXTENSION = ".pjtslvl";
    public static final byte FORMATVER = 7;
    public static final byte LEGACYFORMAT = 7;

    public static final short EMPTYCHUNK = -1;
    public static final short EMPTYROW8 = -2;
    public static final short MULTIPLIER = -3;
    public static final short SEPARATOR = -16;
    public static final short ENDOFFILE = -32;

    public static final String SAVESLOCATION = "\\_data\\saves\\";

    public static void save(String fileName) throws IOException {
        Util.log("Saving file as: " + ROOT + fileName + FILEEXTENSION);
        File file = new File(ROOT + fileName + FILEEXTENSION);
        if (!file.exists()) {
            file.mkdirs();
            file.delete();
            file.createNewFile();
        }

        // exactly
        DataOutputStream dataStream = new DataOutputStream(new FileOutputStream(file, false));

        // first byte of data is save file format version
        // may be useful in the future
        dataStream.writeByte(FORMATVER);
		
		/* == TILE DATA == */
        // chunks are written one by one into file
        for (int i = 0; i < Level.chunks.length; i++) {
            if (Level.chunks[i] == null || Level.chunks[i].tilesN == 0) {
                // -1 is used as token for empty chunks
                dataStream.writeShort(FileManager.EMPTYCHUNK);
            }
            else {
                // filled chunks are checked tile by tile, which are added to the file
                for (int j = 0; j < 64; j++) {
                    if (j <= 56 && Level.chunks[i].tiles[j] == null && Level.chunks[i].tiles[j + 1] == null && Level.chunks[i].tiles[j + 2] == null && Level.chunks[i].tiles[j + 3] == null && Level.chunks[i].tiles[j + 4] == null && Level.chunks[i].tiles[j + 5] == null && Level.chunks[i].tiles[j + 6] == null && Level.chunks[i].tiles[j + 7] == null) {
                        // a row of 8 empty tiles is represented by -2
                        dataStream.writeShort(FileManager.EMPTYROW8);
                        // parser skips ahead 7 tiles
                        j += 7;
                    }
                    // empty tiles are equal to null
                    else if (Level.chunks[i].tiles[j] == null) {
                        dataStream.writeShort(0);
                    }
                    else {
                        dataStream.writeShort(Level.chunks[i].tiles[j].tileID);
                    }
                }
            }
        }
        /* == SEPARATOR == */
        dataStream.writeShort(SEPARATOR);
        /* == SPAWNER DATA == */
        for (int i = 0; i < Level.totalChunks; i++) {
            if (Level.getChunk(i) == null || Level.getChunk(i).spawnersN == 0) {
                // -1 is used as token for empty chunks
                dataStream.writeShort(EMPTYCHUNK);
            } else {
                // filled chunks are checked spawner by spawner, whose entity ids are added to the file
                for (int j = 0; j < 64; j++) {
                    if (j <= 56 && Level.chunks[i].spawners[j] == null && Level.chunks[i].spawners[j + 1] == null && Level.chunks[i].spawners[j + 2] == null && Level.chunks[i].spawners[j + 3] == null && Level.chunks[i].spawners[j + 4] == null && Level.chunks[i].spawners[j + 5] == null && Level.chunks[i].spawners[j + 6] == null && Level.chunks[i].spawners[j + 7] == null) {
                        // a row of 8 empty spawners is represented by -2
                        dataStream.writeShort(FileManager.EMPTYROW8);
                        // parser skips ahead 7 spawners
                        j += 7;
                    }
                    // empty spawners are equal to null
                    else if (Level.chunks[i].spawners[j] == null) {
                        dataStream.writeShort(0);
                    }
                    else {
                        dataStream.writeShort(Level.chunks[i].spawners[j].entityID);
                    }
                }
            }
        }
        /* == SEPARATOR == */
        dataStream.writeShort(SEPARATOR);
        /* == SPAWNPOINT DATA == */
        dataStream.writeBoolean(Spawnpoint.set);
        if (Spawnpoint.set) {
            dataStream.writeInt(Spawnpoint.x);
            dataStream.writeInt(Spawnpoint.y);
        }

        dataStream.writeShort(ENDOFFILE);

        dataStream.flush();	// Java is just one giant joke
        dataStream.close();
    }
    public static void load(String fileName) throws IOException {
        File file = new File(ROOT + fileName + FILEEXTENSION);
        if (!file.exists()) {
            Util.err("File can't be found.");
            return;
        }

        DataInputStream dataStream = new DataInputStream(new FileInputStream(file));

        // save format version is read into memory
        byte formatver = dataStream.readByte();
        if (formatver < LEGACYFORMAT || formatver > FORMATVER) {
            // too old or unknown formats will be disregarded right away
            Util.err("Unknown or unsupported file format. Level file can't be loaded.");
        }
        else {
            // currently loaded level data is cleared
            Level.chunks = new Chunk[Level.totalChunks];

            /* == TILE DATA == */
            for (int i = 0; i < Level.totalChunks; i++) {
                short tileData = dataStream.readShort();

                if (tileData == FileManager.EMPTYCHUNK) {
                    // empty chunks are represented by -1
                    // parser skips ahead to next chunk instead
                    continue;
                }
                else {
                    // new chunk is created if it isn't empty
                    Chunk chunk = Level.getChunk(i);
                    for (int j = 0; j < 64; j++) {
                        if (tileData == FileManager.EMPTYROW8) {
                            // rows of 8 empty tiles are represented by -2
                            // parser skips ahead 7 tiles
                            j += 7;
                        }
                        else if ((tileData != 0) && (tileData != FileManager.EMPTYCHUNK)) {
                            // all regular tile data is read in one by one and added to the chunk
                            chunk.addTile(j, Level.tileset.get(tileData));
                        }

                        if (j < 63) {
                            tileData = dataStream.readShort();
                        }
                    }
                }
            }
            /* == SEPARATOR == */
            short separator = dataStream.readShort();

            if (separator == SEPARATOR) {
                /* == SPAWNER DATA == */
                for (int i = 0; i < Level.totalChunks; i++) {
                    short spawnerData = dataStream.readShort();

                    if (spawnerData == EMPTYCHUNK) {
                        continue;
                    }
                    else {
                        Chunk chunk = Level.getChunk(i);

                        for (int j = 0; j < 64; j++) {
                            if (spawnerData == EMPTYROW8) {
                                j += 7;
                            }
                            else if ((spawnerData != 0) && (spawnerData != EMPTYCHUNK)) {
                                try {
                                    chunk.addSpawner(j, new EntitySpawner(Level.entityset.get(spawnerData).getName(), ((i % 7) * 128) + ((j % 8) * 16), ((i / 7) * 128) + ((j / 8) * 16)));
                                }

                                /*
                                ((i % 8) * 128) + ((j % 8) * 16)
                                ((i / 8) * 128) + ((j / 8) * 16)
                                */


                                catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                    Util.err("Encountered illegal entity ID whilst reading save file.");
                                }
                            }

                            if (j < 63) {
                                spawnerData = dataStream.readShort();
                            }
                        }
                    }
                }
            }
            else {
                Util.err("Encountered strange data while reading save file. File is possibly outdated or corrupted.");
            }

            /* == SEPARATOR == */
            separator = dataStream.readShort();

            if (separator == SEPARATOR) {
                /* == SPAWNPOINT DATA == */
                if (dataStream.readBoolean()) {
                    Spawnpoint.set = true;

                    Spawnpoint.x = dataStream.readInt();
                    Spawnpoint.y = dataStream.readInt();
                }
            }

            /* == END OF FILE == */
            if (dataStream.readShort() != ENDOFFILE) {
                Util.err("Loaded save ended badly. File is possibly outdated or corrupted.");
            }
        }
        dataStream.close();
    }
}