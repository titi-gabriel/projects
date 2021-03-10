import express from 'express';
import {getDonatori, getDonatorById, createDonator, updateDonator, deleteDonator} from '../logic/DonatorLogic.js';

const router = express.Router();
router.route('/donator').get(async (req, res) => {
    try{
        res.status(200).json(await getDonatori());
    } catch(e){
        res.status(500).json({hasErrors: true, message: e.message})
    }
})
router.route('/donator/:id').get(async (req, res) => {
    try{
        res.status(200).json(await getDonatorById(req.params.id));
    } catch(e){
        res.status(500).json({hasErrors: true, message: e.message})
    }
})
router.route('/donator').post(async (req, res) => {
    try {
        let donator = await createDonator(req.body);

        if (donator.hasErrors)
            res.status(400).json(donator);
        else
            res.status(200).json(donator);
    }
    catch (e) {
        res.status(500).json({ hasErrors: true, message: e.message })
    }
})
router.route('/donator/:id').put(async (req, res) => {
    try{
        let donator = await updateDonator(req.params.id, req.body);

        if(donator.hasErrors)
            res.status(400).json(donator);
        else
            res.status(200).json(donator);
    } catch(e)
    {
        res.status(500).json({hasErrors: true, message: e.message})
    }
})
router.route('/donator/:id').delete(async(req, res) => {
    try{
        let donator = await deleteDonator(req.params.id);

        if(donator.hasErrors)
            res.status(400).json(donator);
        else
            res.status(200).json(donator);
    } catch(e)
    {
        res.status(500).json({hasErrors: true, message: e.message})
    }
})

export default router;