package org.springmvc.service;

import org.springmvc.dto.CheckedTab;
import org.springmvc.pojo_inner.RegisterInfoInner;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RegisterInfoLocalService {

    List<CheckedTab> getCheckedListByFlag(String flag, int currIndex, int pageSize, HttpSession httpSession);

    int countCheckListByFlag(String flag);

    int updateRegisterByRegisterLocal(RegisterInfoInner r);
}
