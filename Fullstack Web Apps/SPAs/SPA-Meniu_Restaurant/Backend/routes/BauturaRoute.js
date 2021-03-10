import express from 'express';
import{ validateBautura,createBautura,getBauturi,getBauturaById,updateBautura,deleteBautura} from '../logic/BauturaLogic.js';

const router = express.Router();

router.route('/bautura').get(async (req, res) => {
    try{
        res.status(200).json(await getBauturi());
    } catch(e){
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

router.route('/bautura/:id').get(async (req, res) => {
    try{
        res.status(200).json(await getBauturaById(req.params.id));
    } catch(e){
        res.status(500).json({hasErrors: true, message: e.message})
    }
})



router.route('/bautura').post(async (req, res) => {
    try {
        let bautura = await createBautura(req.body);

        if (bautura.hasErrors)
            res.status(400).json(bautura);
        else
            res.status(200).json(bautura);
    }
    catch (e) {
        res.status(500).json({ hasErrors: true, message: e.message })
    }
})

router.route('/bautura/:id').put(async (req, res) => {
    try{
        let bautura = await updateBautura(req.params.id, req.body);

        if(bautura.hasErrors)
            res.status(400).json(bautura);
        else
            res.status(200).json(bautura);
    } catch(e)
    {
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

router.route('/bautura/:id').delete(async(req, res) => {
    try{
        let bautura = await deleteBautura(req.params.id);

        if(bautura.hasErrors)
            res.status(400).json(bautura);
        else
            res.status(200).json(bautura);
    } catch(e)
    {
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

export default router;