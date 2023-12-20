package com.iqbaal.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalCode {
    private String kecamatan;
    private String kelurahan;
    private String kodepos;
}
