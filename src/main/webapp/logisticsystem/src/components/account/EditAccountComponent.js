import axios from 'axios';
import React from 'react';
import '../account/css/style.css'
import AdminMenu from './AdminMenuComponent';
axios.defaults.withCredentials = true;

class EditAccountComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            account : '',
            staff_id: '',
            name: '',
            phone_num: '',
            enabled: false,
            status: '',
        };
    }
    handleSearch(e){
        const URL = 'http://localhost:8080/account/detail.do';
        if ( !isNaN(e.target.value)){
        this.setState({staff_id : e.target.value});
        axios.post(URL, {
            staff_id :  e.target.value
        },{
            "headers": {
            "content-type": "application/json",
            },
        
        }).then(response => {
            this.setState({account: response.data});
            this.setState({name : this.state.account.name});
            this.setState({phone_num: this.state.account.phone_num});
            this.setState({enabled : this.state.account.enabled})
        },)
    }
        
    }

    // state = {
    //     account : null,
    // };
    
    handlename(e){
        this.setState({name : e.target.value})
        
    }   

    handlephone_num(e){
        this.setState({phone_num : e.target.value})
        
    }

    handleenable(e){
          if(this.state.enabled){
              this.setState({enabled: false});
          } else {
              this.setState({enabled : true});
          }    
    }

    handleUpdate() {
        
        const url = 'http://localhost:8080/account/edit.do';
        const data = 
              {
                  staff_id : this.state.staff_id,
                  name : this.state.name,
                  phone_num : this.state.phone_num,
                  enabled : this.state.enabled,
              } ;    
        let flag = true;
        axios.post(url,data,{
            "headers": {
            "content-type": "application/json",
            },
        },).then(function (response) {
            flag = response.data;
            window.location.reload();
        })

        if (flag) {
            this.setState({status: 'Update Success'})
        }
    }

    render() {
        console.log(this.state.account);
        return (    
            <div className="container">
            <AdminMenu></AdminMenu>
            <h1 style={{marginTop:'70px'}} className="text-center">ACCOUNT EDIT</h1>

            <form className="mainform">
                <div className="form-group row">
                    <label className="col-sm-2 col-form-label">Staff_id</label>
                    <div className="col-sm-10">
                    <input type="text" className="form-control"  onChange={this.handleSearch.bind(this)}></input>
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-2 col-form-label">Name</label>
                    <div className="col-sm-10">
                    <input type="text" className="form-control" defaultValue={this.state.account.name} onChange={this.handlename.bind(this)}></input>
                    </div>
                </div>
                <div className="form-group row"  >
                    <label  className="col-sm-2 col-form-label">Phone_Number</label>
                    <div className="col-sm-10">
                    <input type="text" className="form-control" defaultValue={this.state.account.phone_num} onChange={this.handlephone_num.bind(this)}></input>
                    </div>
                </div>

                <div className="form-group row"  >
                    <label  className="col-sm-2 col-form-label">Role</label>
                    <div className="col-sm-10">
                    <input type="text" className="form-control" defaultValue={this.state.account.role} disabled></input>
                    </div>
                </div>
                {this.state.account.enabled?
                <div className="form-group row">
                    <div className="form-group row">
                    <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" onChange={this.handleenable.bind(this)}></input>
                    <label class="form-check-label" for="exampleCheck1">Enable</label>
                    </div>
                </div>
                </div>
                :
                <div className="form-group row">
                    <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" onChange={this.handleenable.bind(this)} defaultChecked></input>
                    <label class="form-check-label" for="exampleCheck1">Enable</label>
                    </div>
                </div>
                }
               
      
            </form>   
            <div>{this.state.status}</div>
                <div className="form-group button box">
                <div className="col-sm-10">
                <button type="button" className="btn btn-primary"  onClick={() => this.handleUpdate()}>Update</button>
                </div>
            </div>
            
        
            <div className="clear"></div>
        </div>  
        )
    }
}


export default EditAccountComponent 
