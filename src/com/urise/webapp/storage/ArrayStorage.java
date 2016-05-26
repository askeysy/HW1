package com.urise.webapp.storage;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import com.urise.webapp.model.Resume;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 3;

    public void clear() {
        for (int i=0;i<size;i++) storage[i] = null;
    }

    public void update(Resume r) {
        int i=check_resume(r.getUuid());
        if (i>=0) storage[i] = r;
    }

    public void save(Resume r) {
        int i=check_resume(r.getUuid());
        if (i==-1){
            int len_ar=check_size();
            if(len_ar<size){
                storage[len_ar] = r;
            }
            else{
                System.out.println("Array full!");
            }
        }
        else{
            System.out.println("Element is in array!");
        }
    }

    private int check_resume(String uuid) {
        for (int i=0;i<size;i++) {
            if (storage[i]!=null){
                if (uuid == storage[i].getUuid()) return i;
            }
        }
        return -1;
    }

    private int check_size() {
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return 0;
    }

    public Resume get(String uuid) {
        int i=check_resume(uuid);
        if (i!=-1) return storage[i];
        return null;
    }

    public void delete(String uuid) {
        int i=check_resume(uuid);
        if (i!=-1) {
            storage[i] = storage[size - 1];
            storage[size-1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[size];
        return result;
    }

    public int size() {
        return size;
    }
}
