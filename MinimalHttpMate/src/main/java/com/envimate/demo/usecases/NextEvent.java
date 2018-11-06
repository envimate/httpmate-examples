package com.envimate.demo.usecases;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class NextEvent {
    public NextEventResponse process() {
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        final String date = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);

        return new NextEventResponse(date, now.getZone().toString());
    }
}
