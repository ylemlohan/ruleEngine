package xyz.sally.variables.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;
import xyz.sally.variables.dao.VariableDao;
import xyz.sally.variables.domain.dmo.Variable;
import xyz.sally.variables.service.VariableService;
import xyz.sally.variablesapi.domain.dto.VariableDto;
import xyz.sally.variablesapi.domain.request.AddVariableRequest;

import java.util.List;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@Service
public class VariableServiceImpl implements VariableService {

    @Autowired
    private VariableDao variableDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<VariableDto> listVariable() {
        List<Variable> variables = variableDao.listVariable();
        return modelMapper.map(variables, new TypeToken<List<VariableDto>>() {
        }.getType());
    }

    public Response addVariable(AddVariableRequest addVariableRequest) {
        return variableDao.addVariable(modelMapper.map(addVariableRequest, VariableDto.class)) ?
                new Response(ResponseCode.SUCCESS) : new Response(ResponseCode.FAIL);
    }

    public Response deleteVariable(Integer id) {
        return variableDao.deleteById(id) == 1 ? new Response(ResponseCode.SUCCESS) : new Response(ResponseCode.FAIL);
    }
}
