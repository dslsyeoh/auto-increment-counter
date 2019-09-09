/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import java.time.LocalDate;

public interface Updater<T>
{
    void update(T object);

    // Test case purpose
    // In actual use case, will use current date
    void update(T object, LocalDate stimulateDate);
}
