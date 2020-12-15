//
//  MainViewController.swift
//  iosApp
//
//  Created by myungpyo on 2020/12/23.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared

class SearchViewController: UIViewController  {
    
    private let viewModel = MovieViewModel()
    private let disposeBag = CombineDisposeBag()
    
    private var tableViewAdapter: MovieTableViewAdapter?
    
    private var textField = UITextField(frame: .zero)
    private var tableView = UITableView(frame: .zero)
    
    //MARK: - lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupViews()
        self.observeMovies()
    }
    
    deinit {
        disposeBag.dispose()
    }
    
    //MARK: - private func
    private func setupViews() {
        let width = self.view.frame.width
        let height = self.view.frame.height
        
        self.textField.placeholder = "Input keyword"
        self.textField.delegate = self
        self.textField.borderStyle = .roundedRect
        self.textField.clearButtonMode = .whileEditing
        self.textField.frame.origin = CGPoint(x: 0, y: 90)
        self.textField.frame.size = CGSize(width: width, height: 50)
        
        self.view.addSubview(self.textField)
        
        let maxY = self.textField.frame.maxY
        self.tableView.frame.origin = CGPoint(x: 0, y: maxY)
        self.tableView.frame.size = CGSize(width: width, height: height - maxY)
        self.tableView.separatorStyle = .singleLine
        self.tableView.separatorInset = .zero
        
        self.tableViewAdapter = MovieTableViewAdapter(with: self)
        let nib = UINib(nibName: "MovieCell", bundle: Bundle.main)
        self.tableView.register(nib, forCellReuseIdentifier: "MovieCell")
        self.tableView.installAdapter(adapter: tableViewAdapter)
        
        self.view.addSubview(self.tableView)
    }
    
    private func observeMovies() {
        viewModel.$movieViewData.sink { movieViewData in
            switch movieViewData {
            case .empty:
                NSLog("empty")
            case .inProgress:
                NSLog("inProgress")
            case .success(let movies):
                NSLog("success")
                self.tableViewAdapter?.bind(items: movies)
                self.tableView.reloadData()
            case .fail(let error):
                NSLog("fail \(error)")
            }
        }.store(in: disposeBag)
    }
}

extension SearchViewController: UITextFieldDelegate {
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if let text = textField.text, text.count > 0 {
            self.viewModel.serachMovies(with: text)
        }
        
        return true
    }
}

extension SearchViewController: MovieTableViewAdapterDelegate {
    
    func movieTableViewAdapter(_ adapter: MovieTableViewAdapter, toggleScrapOf movie: Movie) {
        self.viewModel.updateScrop(id: movie.id, scrap: !movie.scrap)
    }
}
