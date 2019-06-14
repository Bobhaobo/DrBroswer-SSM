package org.springmvc.service;

import org.springmvc.pojo_inner.DicomWorkListInner;
import org.springmvc.pojo_inner.RegisterInfoInner;

import java.util.List;

public interface RegisterInfoInnerService {

    int saveNewRegisterInfo(RegisterInfoInner registerInfoInner);

}
