package com.urwoo.framework.lang;

public class Arrays {

    public static boolean isEmpty(final String[] str){
        return null == str || str.length <= 0;
    }

    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0) != -1;
    }

    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if(array == null) {
            return -1;
        } else {
            if(startIndex < 0) {
                startIndex = 0;
            }

            int i;
            if(objectToFind == null) {
                for(i = startIndex; i < array.length; ++i) {
                    if(array[i] == null) {
                        return i;
                    }
                }
            } else if(array.getClass().getComponentType().isInstance(objectToFind)) {
                for(i = startIndex; i < array.length; ++i) {
                    if(objectToFind.equals(array[i])) {
                        return i;
                    }
                }
            }

            return -1;
        }
    }
}
