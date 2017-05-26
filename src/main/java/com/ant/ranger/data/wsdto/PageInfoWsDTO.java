package com.ant.ranger.data.wsdto;

import java.util.List;

/**
 * @packgeName: com.ant.ranger.data.dto
 * @ClassName: PageInfoWsDTO
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/4/1-下午12:26
 * @version: 1.0
 * @since: JDK 1.8
 */
public class PageInfoWsDTO {
    private List<CatalogWsDTO> catalogs;

    public PageInfoWsDTO(List<CatalogWsDTO> catalogs) {
        this.catalogs = catalogs;
    }

    public List<CatalogWsDTO> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<CatalogWsDTO> catalogs) {
        this.catalogs = catalogs;
    }
}
