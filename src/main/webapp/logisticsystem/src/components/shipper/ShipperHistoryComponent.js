/*

@author: Huan 

*/

import React from "react";
import axios from "axios";
import ShipperMenu from "./ShipperMenuComponent";
import Cookies from "universal-cookie";
const cookie = new Cookies();

axios.defaults.withCredentials = true;

const SHIPPER_REST_API_URL = "http://localhost:8080/shipper/history";
class ShipperHistoryComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
     // packagelist: [],
     ship_route: [],
    };
  }

  componentDidMount() {
    if (typeof cookie.get('shipper_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    axios.get(SHIPPER_REST_API_URL).then((response) => {
      this.setState({ ship_route: response.data });

      console.log(response.data);
    });
  }

  render() {
    return (
    <div className="container">
      <ShipperMenu />
      <h1 className="mx-auto text-center" style={{marginTop:"70px", padding:"30px"}}>Package List History</h1>
      <div id="accordion" className="row">
          {
            //ship_route.map(route => route.packagelist.map(pkg => pkg.pkg_id))
              //this.state.packagelist.map(
               this.state.ship_route.map(route => route.packagelist.map(
                  pkg => 
                  <div className="col-4 ">
                        <div className=" shadow-lg bg-gray rounded" key={"sk" + pkg.pkg_id} >
                      <div className="card-header p-0" id={"heading" + pkg.pkg_id} >
                          <h5 className="mb-0 rounded" >
                              <button style={{width:"100%"}} className="btn btn-primary" data-toggle="collapse" data-target={"#" + pkg.pkg_id} aria-expanded="true" aria-controls={pkg.pkg_id}>
                             PackageID: {pkg.pkg_id}
                              </button>
                          </h5>
                      </div>

                      <div id={pkg.pkg_id} className="collapse" aria-labelledby={"heading" + pkg.pkg_id} data-parent="#accordion">
                          <div className="card-body">
                              <p><b>Packge ID:</b> {pkg.pkg_id}</p>
                              <hr/>
                              <p><b>Created Time:</b> {pkg.created_datetime}</p>
                              <hr/>
                              <p><b>Delivery Fee:</b> {pkg.delivery_fee}</p>
                              <hr/>
                              <p><b>COD Value: </b>{pkg.cod_value}</p>
                              <hr/>
                              <p><b>Receiver Name:</b> {pkg.receiver_name}</p>
                              <hr/>
                              <p><b>Receiver Phone Number:</b> {pkg.receiver_phone_num}</p>
                              <hr/>
                              <p><b>Receiver Address: </b>{pkg.receiver_address}</p>
                              <hr/>
                              <p><b>Sender Name:</b> {pkg.sender_name}</p>
                              <hr/>
                              <p><b>Sender Phone Number:</b> {pkg.sender_phone_num}</p>
                          </div>
                      </div>
                  </div>
                  </div>
              ))        
  }
      </div>
  </div>
    );
  }
}

export default ShipperHistoryComponent;
