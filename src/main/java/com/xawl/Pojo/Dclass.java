package com.xawl.Pojo;

public class Dclass {
        private Integer id;

        private String cname;
        private Integer pnum;
        private Integer series;
        private String sdept;
        private java.sql.Timestamp starteddate;


        public Integer getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }





        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }


        public Integer getPnum() {
            return pnum;
        }

        public void setPnum(Integer pnum) {
            this.pnum = pnum;
        }


        public Integer getSeries() {
            return series;
        }

        public void setSeries(Integer series) {
            this.series = series;
        }


        public String getSdept() {
            return sdept;
        }

        public void setSdept(String sdept) {
            this.sdept = sdept;
        }


        public java.sql.Timestamp getStarteddate() {
            return starteddate;
        }

        public void setStarteddate(java.sql.Timestamp starteddate) {
            this.starteddate = starteddate;
        }

    }

