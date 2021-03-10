import express from 'express';
import{ validateMancare,createMancare,getMancare,getMancareById,updateMancare,deleteMancare} from '../logic/MancareLogic.js';

const router = express.Router();

router.route('/mancare').get(async (req, res) => {
    try{
        res.status(200).json(await getMancare());
    } catch(e){
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

router.route('/mancare/:id').get(async (req, res) => {
    try{
        res.status(200).json(await getMancareById(req.params.id));
    } catch(e){
        res.status(500).json({hasErrors: true, message: e.message})
    }
})



router.route('/mancare').post(async (req, res) => {
    try {
        let mancare = await createMancare(req.body);

        if (mancare.hasErrors)
            res.status(400).json(mancare);
        else
            res.status(200).json(mancare);
    }
    catch (e) {
        res.status(500).json({ hasErrors: true, message: e.message })
    }
})

router.route('/mancare/:id').put(async (req, res) => {
    try{
        let mancare = await updateMancare(req.params.id, req.body);

        if(mancare.hasErrors)
            res.status(400).json(mancare);
        else
            res.status(200).json(mancare);
    } catch(e)
    {
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

router.route('/mancare/:id').delete(async(req, res) => {
    try{
        let mancare = await deleteMancare(req.params.id);

        if(mancare.hasErrors)
            res.status(400).json(mancare);
        else
            res.status(200).json(mancare);
    } catch(e)
    {
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

export default router;