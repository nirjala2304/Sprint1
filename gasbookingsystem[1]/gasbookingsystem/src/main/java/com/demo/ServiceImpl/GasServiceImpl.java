package com.demo.ServiceImpl;

import com.demo.Dao.GasDao;
import com.demo.DaoImpl.GasDaoImpl;
import com.demo.Service.GasService;
import com.demo.entity.Gas;
import java.util.List;

public class GasServiceImpl implements GasService {

    private GasDao gasDao = new GasDaoImpl();

    @Override
    public void createGas(Gas gas) {
        gasDao.createGas(gas);
    }

    @Override
    public Gas getGasById(int gasId) {
        return gasDao.getGasById(gasId);
    }

    @Override
    public void updateGas(Gas gas) {
        gasDao.updateGas(gas);
    }

    @Override
    public void deleteGas(int gasId) {
        gasDao.deleteGas(gasId);
    }

    @Override
    public List<Gas> getAvailableGases() {
        return gasDao.getAvailableGases();
    }

    @Override
    public void updateGasAvailability(int gasId, boolean isAvailable) {
        gasDao.updateGasAvailability(gasId, isAvailable);
    }
}