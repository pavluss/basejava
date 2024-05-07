package ru.saparsky.basejava.model;

import ru.saparsky.basejava.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link link;
    private final List<Position> positions;

    public Organization(Link link, List<Position> positions) {
        this.link = link;
        this.positions = positions;
    }

    public Organization(String name, String url, Position... positionList) {
        this(new Link(name, url), Arrays.asList(positionList));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!Objects.equals(link, that.link)) return false;
        return Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (positions != null ? positions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" + link + ", " + positions + '}';
    }

    public static class Position implements Serializable {
        private final String title;
        private final String description;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Position(String title, String description, int startYear, Month startMonth) {
            this(title, description, DateUtil.of(startYear, startMonth), DateUtil.NOW);
        }

        public Position(String title, String description, int startYear, Month startMonth, int endYear, Month endMonth) {
            this(title, description, DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth));
        }


        public Position(String title, String description, LocalDate startDate, LocalDate endDate) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (!title.equals(position.title)) return false;
            if (!Objects.equals(description, position.description))
                return false;
            if (!startDate.equals(position.startDate)) return false;
            return endDate.equals(position.endDate);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }
}
