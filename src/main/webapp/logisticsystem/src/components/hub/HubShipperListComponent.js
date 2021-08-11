/* @author: Canh */


import React, { useEffect, useState } from 'react';
import axios from 'axios';
import HubMenu from '../hub/HubMenuComponent'
import Cookies from'universal-cookie'
const cookie = new Cookies();
axios.defaults.withCredentials = true
const HUB_REST_API_URL = 'http://localhost:8080/hub/shipper_list';

const ShipperDetail = function(props) {
    return (
        <div className="card bg-light mb-3 shipper-information" >
            <div className="card-header" style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>{props.shipper.name + " (" + props.shipper.staff_id + ")"}</div>
            <div className="card-body" style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                <h5 className="card-title">{props.shipper.name}</h5>
                <p className="card-text">
                    <span>Phone Number: {props.shipper.phone_num}</span> <br></br>
                    <span>Role: {props.shipper.role}</span> <br></br>
                    <span>Working Hub: {props.shipper.lghub_id}</span>
                </p>
            </div>
        </div>
    );
};

const HubShipperListComponent = function(props) {
    const [shipperList, setShipperList] = useState([]);
    const [shipper, setShipper] = useState({});
    const [checked, setChecked] = useState(true);
    useEffect(() => {
        if (typeof cookie.get('hub_staff_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
        axios.get(HUB_REST_API_URL).then(response => {
            setShipperList(response.data);
            setShipper(response.data[0]);
        });
    },[]);

    return (
        <div className="container" style={{marginTop:"10vh"}}>
                <HubMenu act="shipper" />
                <h1 className="text-center" style={{fontFamily:"Lato-Bold", lineHeight:"1.4", color:"#808080"}}>Shipper List In Hub</h1>
                <div className="shipper-list-show row justify-content-around shadow-lg p-3 mb-5 bg-gray rounded" style={{height: "60vh"}}>
                <div className="shipper-list col-md-7 mh-100 overflow-auto mb-4">
                    <table className="table table-striped">
                        <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                            <tr>
                                <th scope="col">SHIPPER ID</th>
                                <th scope="col">SHIPPER NAME</th>
                                <th scope="col">DETAIL</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                shipperList.map(
                                    shipperDetail => 
                                    <tr key={"shipper" + shipperDetail.staff_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                        <th scope="row">{shipperDetail.staff_id}</th>
                                            <td>{shipperDetail.name}</td>
                                            <td>
                                                <div className="form-check">
                                                    {
                                                        (shipperDetail.staff_id === shipper.staff_id) 
                                                        ? 
                                                        <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault" id={"flexRadioDefault" + shipperDetail.staff_id} onClick={() => {setShipper(shipperDetail)}} defaultChecked={checked} onChange={() => setChecked(!checked)} />
                                                        : 
                                                        <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault" id={"flexRadioDefault" + shipperDetail.staff_id} onClick={() => {setShipper(shipperDetail)}}   />
                                                    }
                                                    
                                                </div>
                                            </td>
                                    </tr>
                                )
         
                            }
                        </tbody>
                    </table>
                </div>
                    <div className="shipper-detail col-md-4">
                    <ShipperDetail shipper={shipper} />
                    </div>
                </div>
            </div>
    );
};

export default HubShipperListComponent