package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import model.MachineMgd;
import model.UniqueId;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Machine extends AbstractEntity {

    public enum SystemType {
        DEBIAN, WINDOWS10, WINDOWS7, FEDORA
    }

    private int CPUs;
    private int RAM;
    private int Disk;
    private MachineMgd.SystemType System;
    private boolean isRented;

    public Machine(int CPUs, int RAM, int Disk, MachineMgd.SystemType System) {
        this.CPUs = CPUs;
        this.RAM = RAM;
        this.Disk = Disk;
        this.System = System;
        this.isRented = isRented;
    }

    public double getBaseCost() throws Exception {
        double price = 1.0;
        if (RAM >= 256 && RAM <= 8192) {
            price += RAM / 10000.0;
        } else if (RAM > 8192 && RAM <= 16384) {
            price += RAM / 7000.0;
        } else if (RAM > 16384 && RAM <= 32768) {
            price += RAM / 5000.0;
        } else if (RAM > 32768 && RAM <= 65536) {
            price += RAM / 3000.0;
        } else {
            throw new Exception("Wrong RAM range given - " + RAM + "! (should be between 256 and 65536)");
        }

        price += (Disk * price) / 1000.0;

        if (CPUs >= 2 && CPUs <= 4) {
            price += (CPUs * price) / 100.0;
        } else if (CPUs > 4 && CPUs <= 8) {
            price += (CPUs * price) / 70.0;
        } else if (CPUs > 8 && CPUs <= 16) {
            price += (CPUs * price) / 50.0;
        } else if (CPUs > 16 && CPUs <= 32) {
            price += (CPUs * price) / 30.0;
        } else {
            throw new Exception("Wrong CPUs range given - " + CPUs + "! (should be between 2 and 32)");
        }

        if (System == MachineMgd.SystemType.WINDOWS7) {
            price += (5 * price) / 100.0;
        } else if (System == MachineMgd.SystemType.WINDOWS10) {
            price += (10 * price) / 100.0;
        } else if (System != MachineMgd.SystemType.FEDORA && System != MachineMgd.SystemType.DEBIAN) {
            throw new Exception("Wrong system name - " + getSystem() + " (options: FEDORA, DEBIAN, WINDOWS7, WINDOWS10)");
        }

        return price;
    }

    public UniqueId getEntityId() {
        return entityId;
    }

    public int getCPUs() {
        return CPUs;
    }

    public int getRAM() {
        return RAM;
    }

    public int getDisk() {
        return Disk;
    }

    public MachineMgd.SystemType getSystem() { return System; }


    public void setEntityId(UniqueId entityId) {
        this.entityId = entityId;
    }
}
