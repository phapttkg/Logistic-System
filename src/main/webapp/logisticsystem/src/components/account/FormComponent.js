import axios from "axios";
import React from "react";
import '../account/css/style.css'



class Sign_upFrom extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            named : '',
            passwd : '',
            phone_num : '',
            role : '',
            hub_id : '',
        }
    }

    handlename(e){
        this.setState({named : e.target.value})
        
    }
    handlepasswd(e){
        this.setState({passwd : e.target.value})
        
    }    
    handlephone_num(e){
        this.setState({phone_num : e.target.value})
        
    }    
    handlerole(e){
        this.setState({role : e.target.value})
        
    }    
    handlehub_id(e){
        this.setState({hub_id : e.target.value})
    }    

    submit() {
        const url = 'http://localhost:8080/account/sign-up.do';
        const data = 
              {
                  name : this.state.named,
                  passwd: this.state.passwd,
                  phone_num : this.state.phone_num,
                  role : this.state.role,
                  lghub_id : this.state.hub_id
              } ;    
        axios.post(url,data,{
            "headers": {
            "content-type": "application/json",
            },
        },).then(function (response) {
            window.location.reload();
        });
        
          
    }
    clear() {
        window.location.reload();
    }

    render () {
    return (
        <div className="container">
        <div> <p></p></div>
        <h1 style={{marginTop:'70px'}} className="text-center">REGISTER FORM</h1>
        <form className="form-left">
            <div className="form-group row">
                <label className="col-sm-2 col-form-label">Name</label>
                <div className="col-sm-10">
                <input type="text" className="form-control" id="named" name="named" placeholder="Your Name" onChange={this.handlename.bind(this)}></input>
                </div>
            </div>
            <div className="form-group row"  >
                <label  className="col-sm-2 col-form-label">Password</label>
                <div className="col-sm-10">
                <input type="password" className="form-control" id="passwd" name="passwd" placeholder="Password" onChange={this.handlepasswd.bind(this)}></input>
                </div>
            </div>
            <div className="form-group row"  >
                <label  className="col-sm-2 col-form-label">Phone_Number</label>
                <div className="col-sm-10">
                <input type="text" className="form-control" id="phone_num" name="phone_num" placeholder="Phone Number" onChange={this.handlephone_num.bind(this)}></input>
                </div>
            </div>
            </form>
            <form className="form-right">
            <div className="form-group row"  >
                <label  className="col-sm-2 col-form-label">Logistic_Hub_Id(Optional)</label>
                <div className="col-sm-10">
                <input type="text" className="form-control" id="hub_id" name="hub_id" placeholder="Logistic Hub Id" onChange={this.handlehub_id.bind(this)}></input>
                </div>
            </div>
            <fieldset className="form-group">
                <div className="row">
                <legend className="col-form-label col-sm-2 pt-0">Role</legend>
                <div className="col-sm-10">

                    <div className="form-check">
                    <input className="form-check-input" type="radio" name="role" id="gridRadios1" value="divider"  onChange={this.handlerole.bind(this)}></input>
                    <label className="form-check-label" >
                        Divider
                    </label>
                    </div>

                    <div className="form-check">
                    <input className="form-check-input" type="radio" name="role" id="gridRadios2" value="hub_staff" onChange={this.handlerole.bind(this)}></input>
                    <label className="form-check-label">
                        Hub Staff
                    </label>
                    </div>

                    <div className="form-check">
                    <input className="form-check-input" type="radio" name="role" id="gridRadios4" value="driver" onChange={this.handlerole.bind(this)} ></input>
                    <label className="form-check-label">
                        Driver
                    </label>
                    </div>

                    <div className="form-check">
                    <input className="form-check-input" type="radio" name="role" id="gridRadios5" value="shipper" onChange={this.handlerole.bind(this)}></input>
                    <label className="form-check-label">
                        Shipper
                    </label>
                    </div>

                </div>
                </div>
            </fieldset>
            {/* <div className="form-group row">
                <div className="col-sm-2">Enable</div>
                <div className="col-sm-10">
                <div className="form-check">
                    <input className="form-check-input" type="checkbox" id="gridCheck1"></input>
                </div>
                </div>
            </div> */}
            
        </form>
        
            <div className="clear"></div>
            <div><h3>{this.state.status}</h3></div>
            <div>
                <form className="form-left">
                <div className="col-sm-10 col-lg-6 buttonleft">
                <button type="button" className="btn btn-primary" onClick={() => this.submit()}>Register</button>
                </div>
                </form>
                <form className="form-right">
                <div className="col-sm-10 col-lg-6 buttonright">
                <button type="button" className="btn btn-primary" onClick={() => this.clear()}>Clear</button>
                </div>
                </form>
            </div>
        </div>
    )
    }
}

export default Sign_upFrom