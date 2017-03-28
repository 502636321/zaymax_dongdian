package com.zaymax.dongdian.config.freemarker;

import com.google.common.base.Strings;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by soy50 on 2017/3/27.
 */
@Component
public class HighlightMethod implements TemplateMethodModelEx {

    @Override
    public Object exec(List list) throws TemplateModelException {
        StringBuffer buffer = new StringBuffer();
        if (!CollectionUtils.isEmpty(list)) {
            String value = (String) list.get(0);
            String highlight = (String) list.get(1);
            if (!Strings.isNullOrEmpty(value)
                    && !Strings.isNullOrEmpty(highlight)) {

            } else {
                buffer.append(value);
            }
        }
        return buffer;
    }

}
